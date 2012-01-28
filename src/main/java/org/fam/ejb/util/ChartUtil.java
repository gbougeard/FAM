/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fam.ejb.util;

import com.googlecode.charts4j.*;
import org.fam.common.log.LogUtil;
import org.fam.ejb.model.FamPlayerProfile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.logging.Level;

/**
 *
 * @author mask_hot
 */
public class ChartUtil {
    
    public static final Logger LOGGER = LoggerFactory.getLogger(ChartUtil.class);


    public static String radarProfile(FamPlayerProfile profile) {

        LOGGER.trace("radarProfile : " + profile.toString());

        if (profile != null) {

            RadarPlot plot = Plots.newRadarPlot(Data.newData(profile.getAtt(),
                    profile.getTec(),
                    profile.getPhy(),
                    profile.getDef(),
                    profile.getPui(),
                    profile.getVit(),
                    profile.getAtt()));

            String url = buildRadarProfile(plot).toURLString();
            return url;


        } else {
            return null;
        }
    }

    private static RadarChart buildRadarProfile(RadarPlot plot) {
        Color plotColor = Color.newColor("CC3366");
//        plot.addShapeMarkers(Shape.SQUARE, plotColor, 12);
//        plot.addShapeMarkers(Shape.SQUARE, Color.WHITE, 8);
        plot.setColor(plotColor);
        plot.setLineStyle(LineStyle.newLineStyle(4, 1, 0));
        plot.setFillAreaColor(Color.LIGHTSTEELBLUE);

        RadarChart chart = GCharts.newRadarChart(plot);
        chart.setTitle("Profil", Color.BLACK, 20);
        chart.setSize(400, 400);

        //TODO : Bundle Labels & Legends
        RadialAxisLabels radialAxisLabels = AxisLabelsFactory.newRadialAxisLabels("ATT", "TEC", "PHY", "DEF", "PUI", "VIT");
        radialAxisLabels.setRadialAxisStyle(Color.BLACK, 12);
        chart.addRadialAxisLabels(radialAxisLabels);
        AxisLabels contrentricAxisLabels = AxisLabelsFactory.newNumericAxisLabels(Arrays.asList(0, 20, 40, 60, 80, 100));
        contrentricAxisLabels.setAxisStyle(AxisStyle.newAxisStyle(Color.BLACK, 12, AxisTextAlignment.RIGHT));
        chart.addConcentricAxisLabels(contrentricAxisLabels);

        return chart;
    }
}
