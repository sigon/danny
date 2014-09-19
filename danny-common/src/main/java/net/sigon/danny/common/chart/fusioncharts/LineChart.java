package net.sigon.danny.common.chart.fusioncharts;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Sguang
 * Date: 14-8-23
 * Time: 上午9:44
 * To change this template use File | Settings | File Templates.
 */
public class LineChart {
    private Chart chart;
    private List<Node> data;
    private List<Trendline> trendlines;

    public Chart getChart() {
        return chart;
    }

    public void setChart(Chart chart) {
        this.chart = chart;
    }

    public List<Node> getData() {
        return data;
    }

    public void setData(List<Node> data) {
        this.data = data;
    }

    public List<Trendline> getTrendlines() {
        return trendlines;
    }

    public void setTrendlines(List<Trendline> trendlines) {
        this.trendlines = trendlines;
    }
}
