package org.hbrs.se2.gui.components;

/**
 *
 * @author Felix
 */
public class KuchenDiagramm {

//    @Override
//    protected Component getChart() {
//        Component ret = createChart();
//        ret.setWidth("100%");
//        ret.setHeight("450px");
//        return ret;
//    }
//
//    private Component createChart() {
//        Chart chart = new Chart(ChartType.PIE);
//
//        Configuration conf = chart.getConfiguration();
//
//        conf.setTitle("Browser Uebersicht");
//
//        PlotOptionsPie plotOptions = new PlotOptionsPie();
//        plotOptions.setCursor(Cursor.POINTER);
//        Labels dataLabels = new Labels();
//        dataLabels.setEnabled(true);
//        dataLabels.setFormatter("''+ this.point.name +': '+ this.percentage +' %'");
//        plotOptions.setDataLabels(dataLabels);
//        conf.setPlotOptions(plotOptions);
//
//        final DataSeries series = new DataSeries();
//        series.add(new DataSeriesItem("Firefox", 45.0));
//        series.add(new DataSeriesItem("IE", 26.8));
//        DataSeriesItem chrome = new DataSeriesItem("Chrome", 12.8);
//        chrome.setSliced(true);
//        chrome.setSelected(true);
//        series.add(chrome);
//        series.add(new DataSeriesItem("Safari", 8.5));
//        series.add(new DataSeriesItem("Opera", 6.2));
//        series.add(new DataSeriesItem("Others", 0.7));
//        conf.setSeries(series);
//
//        chart.addPointClickListener(new PointClickListener() {
//            @Override
//            public void onClick(PointClickEvent event) {
//                Notification.show("Click: "
//                        + series.get(event.getPointIndex()).getName());
//            }
//        });
//
//        chart.drawChart(conf);
//
//        return chart;
//    }

}
