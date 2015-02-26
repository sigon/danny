package net.sigon.danny.common.forex.sina;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Modifier;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import net.sigon.danny.common.forex.ForexFactory;
import net.sigon.danny.common.forex.ForexItem;

public class SinaForexFactory implements ForexFactory {

	private static final String URL = "http://stock.finance.sina.com.cn/forex/api/openapi.php/ForexService.getAllBankForex";

	private URL url;
	private GsonBuilder builder;

	public SinaForexFactory() {
		builder = new GsonBuilder();
		builder.excludeFieldsWithModifiers(Modifier.STATIC, Modifier.TRANSIENT, Modifier.VOLATILE);
	}

	@Override
	public List<ForexItem> execute(List<String> currencies) throws Exception {
		HttpURLConnection conn = null;
		try {
			url = new URL(String.format(URL, Calendar.getInstance().getTime().getTime()));
			conn = (HttpURLConnection) url.openConnection();
			addHttpReader(conn);
			conn.connect();
			InputStream stream = new BufferedInputStream(conn.getInputStream());
			byte[] buf = new byte[1024];
			StringBuffer sb = new StringBuffer();
			int len = stream.read(buf);
			while (len > 0) {
				sb.append(new String(buf, 0, len));
				len = stream.read(buf);
			}
			stream.close();
			List<RateItem> list = processResponse(sb.toString());
			return updateRates(list, currencies);

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.disconnect();
			}
		}
		return null;
	}

	private List<ForexItem> updateRates(List<RateItem> list, List<String> currencies) {
		if (list != null) {
			List<ForexItem> forexList = new ArrayList<ForexItem>();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			for (RateItem item : list) {
				try {
					if (item.getSymbol() != null && currencies.contains(item.getSymbol().concat("CNY")) && item.getXh_buy() != null && item.getXh_sell() != null) {
						String symbol = item.getSymbol().concat("CNY");
						Double weBuy = Double.parseDouble(item.getXh_sell()) * 1000;
						Double weSell = Double.parseDouble(item.getXh_buy()) * 1000;
						Date utime = sdf.parse(item.getUdate() + " " + item.getUtime());
						ForexItem forex = new ForexItem();
						forex.setSymbol(symbol);
						forex.setXhBuy(weBuy.intValue());
						forex.setXhSell(weSell.intValue());
						forex.setModifyDate(utime);
						forexList.add(forex);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return forexList;
		}
		return null;

	}

	private List<RateItem> processResponse(String jsonText) {
		Gson gson = builder.create();
		JsonElement je = gson.fromJson(jsonText, JsonElement.class);
		JsonElement result = getJsonElement(je, "result");
		JsonElement data = getJsonElement(result, "data");
		// JsonElement boc = getJsonElement(data, "boc");
		SinaResponse resp = gson.fromJson(data, SinaResponse.class);
		List<RateItem> list = resp.getBoc();
		return list;
	}

	private JsonElement getJsonElement(JsonElement element, String key) {
		if (element == null) {
			return null;
		}
		if (element.isJsonObject()) {
			return element.getAsJsonObject().get(key);
		} else {
			return null;
		}
	}

	private void addHttpReader(HttpURLConnection conn) {
		conn.addRequestProperty("User-Agent",
				"Mozilla/5.0 (X11; U; Linux x86_64; en-US; rv:1.9.1b4) Gecko/20090427 Fedora/3.5-0.20.beta4.fc11 Firefox/3.5b4");
		conn.addRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
		conn.addRequestProperty("Accept-Language", "en-US,en;q=0.8,zh-CN;q=0.6");
		conn.addRequestProperty("Connection", "keep-alive");
		conn.addRequestProperty("Cache-Control", "no-cache");
	}

	public static void main(String[] args) throws Exception {
		ForexFactory factory = new SinaForexFactory();
		List<ForexItem> list = factory.execute(Arrays.asList("AUDCNY", "NZDCNY", "USDCNY"));
		for(ForexItem item: list){
			System.out.print(item.getSymbol());
			System.out.print(" ");
			System.out.print(item.getXhBuy());
			System.out.print(" ");
			System.out.print(item.getXhSell());
			System.out.print(" ");
			System.out.println(item.getModifyDate());
		}
	}
}
