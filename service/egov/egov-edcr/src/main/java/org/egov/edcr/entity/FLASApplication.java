package org.egov.edcr.entity;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.LAZY;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.egov.common.entity.edcr.PlanInformation;
import org.egov.infra.admin.master.entity.User;
import org.egov.infra.persistence.entity.AbstractAuditable;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name = "flas_rooms")
@SequenceGenerator(name = FLASApplication.SEQ_EDCR_FLAS, sequenceName = FLASApplication.SEQ_EDCR_FLAS, allocationSize = 1)
public class FLASApplication {
	/*
	 * Application number and date.Owner name, contact info,email id, address,
	 * Architect name, emailid,contract info.
	 */
	public static final String SEQ_EDCR_FLAS = "SEQ_EDCR_FLAS";
	private static final long serialVersionUID = 61L;

	@Id
	@GeneratedValue(generator = SEQ_EDCR_FLAS, strategy = GenerationType.SEQUENCE)
	private Long id;

	private String applicationNumber;
	
	@NotNull
	@Length(min = 1, max = 128)
	private String name;

	@Temporal(value = TemporalType.DATE)
	private Date createddate;

	private String area;
//
//	@OneToMany(mappedBy = "application", fetch = LAZY, cascade = ALL)
//	@OrderBy("id DESC ")
//	private List<EdcrApplicationDetail> edcrApplicationDetails;

	@Length(min = 1, max = 128)
	private String minHeight;

	@Length(min = 1, max = 128)
	private String maxHeight;

	@Length(min = 1, max = 128)
	private String avgHeight;

	@Length(min = 1, max = 128)
	private String length;

	@Length(min = 1, max = 128)
	private String breadth;

	@Length(min = 1, max = 128)
	private String totalArea;

	@Length(min = 1, max = 128)
	private String breathingSpace;

	// add for coverage areaCategory
//    private transient String areaCategory;

	public Long getId() {
		return id;
	}

	protected void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getCreateddate() {
		return createddate;
	}

	public void setCreateddate(Date createddate) {
		this.createddate = createddate;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getMinHeight() {
		return minHeight;
	}

	public void setMinHeight(String minHeight) {
		this.minHeight = minHeight;
	}

	public String getMaxHeight() {
		return maxHeight;
	}

	public void setMaxHeight(String maxHeight) {
		this.maxHeight = maxHeight;
	}

	public String getAvgHeight() {
		return avgHeight;
	}

	public void setAvgHeight(String avgHeight) {
		this.avgHeight = avgHeight;
	}

	public String getLength() {
		return length;
	}

	public void setLength(String length) {
		this.length = length;
	}

	public String getBreadth() {
		return breadth;
	}

	public void setBreadth(String breadth) {
		this.breadth = breadth;
	}

	public String getTotalArea() {
		return totalArea;
	}

	public void setTotalArea(String totalArea) {
		this.totalArea = totalArea;
	}

	public String getBreathingSpace() {
		return breathingSpace;
	}

	public void setBreathingSpace(String breathingSpace) {
		this.breathingSpace = breathingSpace;
	}

	public String getApplicationNumber() {
		return applicationNumber;
	}

	public void setApplicationNumber(String applicationNumber) {
		this.applicationNumber = applicationNumber;
	}

	
	
}
