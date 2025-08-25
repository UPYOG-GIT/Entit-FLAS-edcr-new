/*
 * eGov  SmartCity eGovernance suite aims to improve the internal efficiency,transparency,
 * accountability and the service delivery of the government  organizations.
 *
 *  Copyright (C) <2019>  eGovernments Foundation
 *
 *  The updated version of eGov suite of products as by eGovernments Foundation
 *  is available at http://www.egovernments.org
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program. If not, see http://www.gnu.org/licenses/ or
 *  http://www.gnu.org/licenses/gpl.html .
 *
 *  In addition to the terms of the GPL license to be adhered to in using this
 *  program, the following additional terms are to be complied with:
 *
 *      1) All versions of this program, verbatim or modified must carry this
 *         Legal Notice.
 *      Further, all user interfaces, including but not limited to citizen facing interfaces,
 *         Urban Local Bodies interfaces, dashboards, mobile applications, of the program and any
 *         derived works should carry eGovernments Foundation logo on the top right corner.
 *
 *      For the logo, please refer http://egovernments.org/html/logo/egov_logo.png.
 *      For any further queries on attribution, including queries on brand guidelines,
 *         please contact contact@egovernments.org
 *
 *      2) Any misrepresentation of the origin of the material is prohibited. It
 *         is required that all modified versions of this material be marked in
 *         reasonable ways as different from the original version.
 *
 *      3) This license does not grant any rights to any user of the program
 *         with regards to rights under trademark law for use of the trade names
 *         or trademarks of eGovernments Foundation.
 *
 *  In case of any queries, you can reach eGovernments Foundation at contact@egovernments.org.
 */

package org.egov.common.entity.edcr;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class FLASToilet {

	private String name;

	private BigDecimal breadth;

	private BigDecimal length;

	private BigDecimal averageHeight;

	private BigDecimal minHeight;

	private BigDecimal maxHeight;

	private List<Door> doors;

	private List<Window> windows;

	private BigDecimal totalArea;

	private BigDecimal breathingSpace;

	private String number;

	private List<RoomHeight> heightOfRooms = new ArrayList<>();

	private Boolean closed = false;

	private List<Measurement> rooms = new ArrayList<>();

	private List<Measurement> ventilation = new ArrayList<>();

	private MeasurementWithHeight lightAndVentilation = new MeasurementWithHeight();

	private List<Occupancy> mezzanineAreas = new ArrayList<>();

	public List<RoomHeight> getHeights() {
		return heightOfRooms;
	}

	public void setHeights(List<RoomHeight> heights) {
		this.heightOfRooms = heights;
	}

	/**
	 * @return the closed
	 */
	public Boolean getClosed() {
		return closed;
	}

	/**
	 * @param closed the closed to set
	 */
	public void setClosed(Boolean closed) {
		this.closed = closed;
	}

	/**
	 * @return the number
	 */
	public String getNumber() {
		return number;
	}

	/**
	 * @param number the number to set
	 */
	public void setNumber(String number) {
		this.number = number;
	}

	/**
	 * @return the lightAndVentilation
	 */
	public MeasurementWithHeight getLightAndVentilation() {
		return lightAndVentilation;
	}

	/**
	 * @param lightAndVentilation the lightAndVentilation to set
	 */
	public void setLightAndVentilation(MeasurementWithHeight lightAndVentilation) {
		this.lightAndVentilation = lightAndVentilation;
	}

	public List<Measurement> getRooms() {
		return rooms;
	}

	public void setRooms(List<Measurement> rooms) {
		this.rooms = rooms;
	}

	public List<Occupancy> getMezzanineAreas() {
		return mezzanineAreas;
	}

	public void setMezzanineAreas(List<Occupancy> mezzanineAreas) {
		this.mezzanineAreas = mezzanineAreas;
	}

	public List<Measurement> getVentilation() {
		return ventilation;
	}

	public void setVentilation(List<Measurement> ventilation) {
		this.ventilation = ventilation;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getBreadth() {
		return breadth;
	}

	public void setBreadth(BigDecimal breadth) {
		this.breadth = breadth;
	}

	public BigDecimal getLength() {
		return length;
	}

	public void setLength(BigDecimal length) {
		this.length = length;
	}

	public BigDecimal getAverageHeight() {
		return averageHeight;
	}

	public void setAverageHeight(BigDecimal averageHeight) {
		this.averageHeight = averageHeight;
	}

	public BigDecimal getMinHeight() {
		return minHeight;
	}

	public void setMinHeight(BigDecimal minHeight) {
		this.minHeight = minHeight;
	}

	public BigDecimal getMaxHeight() {
		return maxHeight;
	}

	public void setMaxHeight(BigDecimal maxHeight) {
		this.maxHeight = maxHeight;
	}

	public List<Door> getDoors() {
		return doors;
	}

	public void setDoors(List<Door> doors) {
		this.doors = doors;
	}

	public List<Window> getWindows() {
		return windows;
	}

	public void setWindows(List<Window> windows) {
		this.windows = windows;
	}

	public BigDecimal getTotalArea() {
		return totalArea;
	}

	public void setTotalArea(BigDecimal totalArea) {
		this.totalArea = totalArea;
	}

	public BigDecimal getBreathingSpace() {
		return breathingSpace;
	}

	public void setBreathingSpace(BigDecimal breathingSpace) {
		this.breathingSpace = breathingSpace;
	}

}
