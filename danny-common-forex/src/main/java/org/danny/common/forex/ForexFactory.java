package org.danny.common.forex;


import java.util.List;

public interface ForexFactory{

	public List<ForexItem> execute(List<String> currency) throws Exception;


}
