package org.danny.common.forex.sina;

import java.util.List;

public class SinaResponse {

	private List<RateItem> boc;

	public List<RateItem> getBoc() {
		return boc;
	}

	public void setBoc(List<RateItem> boc) {
		this.boc = boc;
	}

}
