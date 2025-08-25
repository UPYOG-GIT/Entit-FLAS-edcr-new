package org.egov.edcr.feature;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.egov.common.entity.edcr.FLASRoom;
import org.egov.common.entity.edcr.FLASToilet;
import org.egov.edcr.entity.blackbox.PlanDetail;
import org.egov.edcr.service.LayerNames;
import org.egov.edcr.utility.Util;
import org.kabeja.dxf.DXFLWPolyline;
import org.kabeja.dxf.DXFVertex;
import org.kabeja.dxf.helpers.Point;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HrFlasExtract extends FeatureExtract {
	private static final Logger LOG = LogManager.getLogger(HrFlasExtract.class);
	@Autowired
	private LayerNames layerNames;

	@Override
	public PlanDetail extract(PlanDetail pl) {

		if (LOG.isDebugEnabled())
			LOG.debug("Starting Coverage Extract......");
//		DXFLayer layer = pl.getDoc().getDXFLayer("BLK_1_FLR_0_R_1");
//		List<Measurement> list = new ArrayList<>();

		List<FLASRoom> flasRooms = new ArrayList<>();

		List<FLASToilet> flasToilets = new ArrayList<>();

		BigDecimal minHeight = BigDecimal.ZERO;
		BigDecimal maxHeight = BigDecimal.ZERO;
		BigDecimal avgHeight = BigDecimal.ZERO;

//		List<Measurement> roomMeasurements;
//		List<Measurement> roomToiletMeasurements;

		List<BigDecimal> floorHeight = Util.getListOfDimensionValueByLayer(pl, "BLK_1_FLR_0_HEIGHT");

		if (floorHeight != null && !floorHeight.isEmpty()) {
			minHeight = floorHeight.stream().min(BigDecimal::compareTo).orElse(BigDecimal.ZERO);
			maxHeight = floorHeight.stream().max(BigDecimal::compareTo).orElse(BigDecimal.ZERO);

			BigDecimal sum = floorHeight.stream().reduce(BigDecimal.ZERO, BigDecimal::add);

			avgHeight = sum.divide(BigDecimal.valueOf(floorHeight.size()), 2, BigDecimal.ROUND_HALF_UP);
		}

		String roomsLayerName = String.format("BLK_1_FLR_0_R_%s", "+\\d");

		List<String> roomsLayers = Util.getLayerNamesLike(pl.getDoc(), roomsLayerName);

//		List<DXFLWPolyline> polylinesRooms = new ArrayList<>();

		if (!roomsLayers.isEmpty()) {
			for (String layerName : roomsLayers) {
				List<DXFLWPolyline> dimentionList = Util.getPolyLinesByLayer(pl.getDoc(), layerName);
				String name = Util.getMtextByLayerName(pl.getDoc(), layerName);

				for (DXFLWPolyline polyline : dimentionList) {
					FLASRoom flasRoom = new FLASRoom();
					double minX = Double.MAX_VALUE;
					double maxX = -Double.MAX_VALUE;
					double minY = Double.MAX_VALUE;
					double maxY = -Double.MAX_VALUE;

//					BigDecimal area = Util.getPolyLineArea(polyline);

					int vertexCount = polyline.getVertexCount();
					for (int i = 0; i < vertexCount; i++) {
						DXFVertex vertex = polyline.getVertex(i); // ✅ correct method
						Point p = vertex.getPoint(); // ✅ get the Point
						double x = p.getX();
						double y = p.getY();

						if (x < minX)
							minX = x;
						if (x > maxX)
							maxX = x;
						if (y < minY)
							minY = y;
						if (y > maxY)
							maxY = y;
					}

					double length = maxX - minX;
					double breadth = maxY - minY;

					BigDecimal breathingSpace = avgHeight.multiply(BigDecimal.valueOf(breadth))
							.multiply(BigDecimal.valueOf(length));

					BigDecimal totalArea = BigDecimal.valueOf(breadth).multiply(BigDecimal.valueOf(length));

					flasRoom.setTotalArea(totalArea);
					flasRoom.setBreathingSpace(breathingSpace);
					flasRoom.setBreadth(BigDecimal.valueOf(breadth));
					flasRoom.setLength(BigDecimal.valueOf(length));
					flasRoom.setMaxHeight(maxHeight);
					flasRoom.setMinHeight(minHeight);
					flasRoom.setAverageHeight(avgHeight);
					flasRoom.setName(name);

					flasRooms.add(flasRoom);

//					System.out.println("Length: " + length + ", Breadth: " + breadth);
				}

//				polylinesRooms.addAll(dimentionList);
			}
		}

		String toiletLayerName = String.format("BLK_1_FLR_0_T_%s", "+\\d");

		List<String> toiletLayers = Util.getLayerNamesLike(pl.getDoc(), toiletLayerName);

//		List<DXFLWPolyline> polylinesToilet = new ArrayList<>();

		if (!toiletLayers.isEmpty()) {
			for (String layerName : toiletLayers) {
				List<DXFLWPolyline> dimentionList = Util.getPolyLinesByLayer(pl.getDoc(), layerName);

				String name = Util.getMtextByLayerName(pl.getDoc(), layerName);

				for (DXFLWPolyline polyline : dimentionList) {
					FLASToilet flasToilet = new FLASToilet();
					double minX = Double.MAX_VALUE;
					double maxX = -Double.MAX_VALUE;
					double minY = Double.MAX_VALUE;
					double maxY = -Double.MAX_VALUE;

//					BigDecimal area = Util.getPolyLineArea(polyline);

					int vertexCount = polyline.getVertexCount();
					for (int i = 0; i < vertexCount; i++) {
						DXFVertex vertex = polyline.getVertex(i); // ✅ correct method
						Point p = vertex.getPoint(); // ✅ get the Point
						double x = p.getX();
						double y = p.getY();

						if (x < minX)
							minX = x;
						if (x > maxX)
							maxX = x;
						if (y < minY)
							minY = y;
						if (y > maxY)
							maxY = y;
					}

					double length = maxX - minX;
					double breadth = maxY - minY;

					BigDecimal breathingSpace = avgHeight.multiply(BigDecimal.valueOf(breadth))
							.multiply(BigDecimal.valueOf(length));

					BigDecimal totalArea = BigDecimal.valueOf(breadth).multiply(BigDecimal.valueOf(length));

					flasToilet.setTotalArea(totalArea);
					flasToilet.setBreathingSpace(breathingSpace);
					flasToilet.setBreadth(BigDecimal.valueOf(breadth));
					flasToilet.setLength(BigDecimal.valueOf(length));
					flasToilet.setMaxHeight(maxHeight);
					flasToilet.setMinHeight(minHeight);
					flasToilet.setAverageHeight(avgHeight);
					flasToilet.setName(name);

					flasToilets.add(flasToilet);

//					System.out.println("Length: " + length + ", Breadth: " + breadth);
				}

//				polylinesToilet.addAll(dimentionList);
			}
		}

		pl.setFlasRooms(flasRooms);
		pl.setFlasToilets(flasToilets);

		/*
		 * // List<DXFLWPolyline> polylinesCoverage =
		 * Util.getPolyLinesByLayer(pl.getDoc(), "BLK_1_FLR_0_R_1"); List<DXFLWPolyline>
		 * polylinesCoverage1 = Util.getPolyLinesByLayer(pl.getDoc(),
		 * "BLK_1_FLR_0_R_1_T_1");
		 * 
		 * List<DXFDimension> dimensionListRoom = Util.getDimensionsByLayer(pl.getDoc(),
		 * "BLK_1_FLR_0_R_1"); List<DXFDimension> dimensionListRoomToilet =
		 * Util.getDimensionsByLayer(pl.getDoc(), "BLK_1_FLR_0_R_1_T_1"); String
		 * doorHeight = Util.getMtextByLayerName(pl.getDoc(), "BLK_1_FLR_0_R_1");
		 * roomMeasurements = polylinesCoverage.stream().map(flightPolyLine -> new
		 * MeasurementDetail(flightPolyLine, true)) .collect(Collectors.toList());
		 * roomToiletMeasurements = polylinesCoverage1.stream() .map(flightPolyLine ->
		 * new MeasurementDetail(flightPolyLine, true)).collect(Collectors.toList());
		 * for (DXFLWPolyline polyline : polylinesCoverage) { Measurement measurement =
		 * new MeasurementDetail(polyline, true); list.add(measurement); }
		 * 
		 * for (DXFLWPolyline polyline : polylinesCoverage) { double minX =
		 * Double.MAX_VALUE; double maxX = -Double.MAX_VALUE; double minY =
		 * Double.MAX_VALUE; double maxY = -Double.MAX_VALUE;
		 * 
		 * int vertexCount = polyline.getVertexCount(); for (int i = 0; i < vertexCount;
		 * i++) { DXFVertex vertex = polyline.getVertex(i); // ✅ correct method Point p
		 * = vertex.getPoint(); // ✅ get the Point double x = p.getX(); double y =
		 * p.getY();
		 * 
		 * if (x < minX) minX = x; if (x > maxX) maxX = x; if (y < minY) minY = y; if (y
		 * > maxY) maxY = y; }
		 * 
		 * double length = maxX - minX; double breadth = maxY - minY;
		 * 
		 * System.out.println("Length: " + length + ", Breadth: " + breadth); }
		 * 
		 * List<BigDecimal> values = Util.getListOfDimensionValueByLayer(pl,
		 * "BLK_1_FLR_0_R_1"); System.out.println(" list " + list.toString());
		 * System.out.println("roomMeasurements " + roomMeasurements.toString());
		 * System.out.println("roomToiletMeasurements " +
		 * roomToiletMeasurements.toString()); System.out.println("values " +
		 * values.toString()); // pl.setFlasRooms(roomMeasurements); //
		 * pl.setFlasToilet(roomToiletMeasurements);
		 * 
		 * Map<Integer, List<BigDecimal>> roomHeightMap = new HashMap<>();
		 * 
		 * String regularRoomLayerName = String.format("BLK_1_FLR_0_R_%s", "+\\d");
		 * 
		 * List<String> regularRoomLayers = Util.getLayerNamesLike(pl.getDoc(),
		 * regularRoomLayerName); Room room = new Room(); if
		 * (!regularRoomLayers.isEmpty()) {
		 * 
		 * for (String regularRoomLayer : regularRoomLayers) {
		 * 
		 * List<DXFLWPolyline> roomPolyLines = Util.getPolyLinesByLayer(pl.getDoc(),
		 * regularRoomLayer);
		 * 
		 * if (!roomHeightMap.isEmpty() || !roomPolyLines.isEmpty()) {
		 * 
		 * boolean isClosed = roomPolyLines.stream().allMatch(dxflwPolyline ->
		 * dxflwPolyline.isClosed());
		 * 
		 * String[] roomNo = regularRoomLayer.split("_"); if (roomNo != null &&
		 * roomNo.length == 7) { room.setNumber(roomNo[6]); } room.setClosed(isClosed);
		 * 
		 * List<RoomHeight> roomHeights = new ArrayList<>(); if
		 * (!roomPolyLines.isEmpty()) { List<Measurement> rooms = new
		 * ArrayList<Measurement>(); roomPolyLines.stream().forEach(rp -> { Measurement
		 * m = new MeasurementDetail(rp, true); if (!roomHeightMap.isEmpty() &&
		 * roomHeightMap.containsKey(m.getColorCode())) { for (BigDecimal value :
		 * roomHeightMap.get(m.getColorCode())) { RoomHeight roomHeight = new
		 * RoomHeight(); roomHeight.setColorCode(m.getColorCode());
		 * roomHeight.setHeight(value); roomHeights.add(roomHeight); }
		 * room.setHeights(roomHeights); } rooms.add(m); }); // Extract the Mezzanine
		 * Area if is declared at ac room level room.setRooms(rooms); } } } }
		 * 
		 * System.out.println("room " + room.toString()); Measurement measurement = new
		 * Measurement();
		 * 
		 * List<DXFLWPolyline> roadAreaLayerNames =
		 * Util.getPolyLinesByLayer(pl.getDoc(), "BLK_1_FLR_0_R_1");
		 * 
		 * BigDecimal roadArea = BigDecimal.ZERO; for (DXFLWPolyline
		 * roadAreaLayerPolyLine : roadAreaLayerNames) { Util.setDimension(measurement,
		 * roadAreaLayerPolyLine); // ((PlotDetail)
		 * pl.getPlot()).setPolyLine(roadAreaLayerPolyLine); roadArea =
		 * roadArea.add(Util.getPolyLineArea(roadAreaLayerPolyLine)); } // for (Block
		 * block : pl.getBlocks()) { // List<DXFLWPolyline> polylinesCoverage =
		 * Util.getPolyLinesByLayer(pl.getDoc(), //
		 * String.format(layerNames.getLayerName("LAYER_NAME_COVERAGE"),
		 * block.getNumber())); // List<DXFLWPolyline> polylinesCoverageDeduct =
		 * Util.getPolyLinesByLayer(pl.getDoc(), //
		 * String.format(layerNames.getLayerName("LAYER_NAME_COVERAGE_DEDUCT"),
		 * block.getNumber())); // for (DXFLWPolyline polyline : polylinesCoverage) { //
		 * Measurement measurement = new MeasurementDetail(polyline, false); //
		 * block.getCoverage().add(measurement); // } // for (DXFLWPolyline polyline :
		 * polylinesCoverageDeduct) { // Measurement measurement = new
		 * MeasurementDetail(polyline, false); //
		 * block.getCoverageDeductions().add(measurement); // } // }
		 */

		if (LOG.isDebugEnabled())
			LOG.debug("Starting Coverage Extract......");
		return pl;
	}

	@Override
	public PlanDetail validate(PlanDetail pl) {
		return pl;
	}

}