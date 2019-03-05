package com.springboot;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity // This tells Hibernate to make a table out of this class
public class Database_columns{
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    private String tid;
    private String patient_name;
    private String hospital_code;
    private String hospital_name;
    private String hospital_type;
    private String pkg_code;
    private String pkg_name;
    private Integer pkg_rate;
    private String id_type;
    private String id_number;
    private String district_name;
    private long aadhar_number;
    private String policy_year;
    private long mobile;
    private String status;
    private String claim_number;
    private String gender;
    private Integer age;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void set_details(String tid, String patient_name, String hospital_code, String hospital_name, String hospital_type, String pkg_code, String pkg_name,
	 Integer pkg_rate, String id_type, String id_number, String district_name, long aadhar_number, String policy_year, long mobile,
		String status, String claim_number, String gender, Integer age) 
	{
		this.tid = tid;
		this.patient_name = patient_name;
		this.hospital_code = hospital_code;
		this.hospital_name = hospital_name; 	
		this.hospital_type = hospital_type;
		this.pkg_code = pkg_code;
		this.pkg_name = pkg_name;
		this.pkg_rate = pkg_rate;
		this.id_type = id_type;
		this.id_number = id_number;
		this.district_name = district_name;
		this.aadhar_number = aadhar_number;
		this.policy_year = policy_year;
		this.mobile = mobile;
		this.status = status;
		this.claim_number = claim_number;
		this.gender = gender;
		this.age = age;

	}

	public String get_patient_name() {
		return patient_name;
	}

	public String get_hospital_code() {
		return hospital_code;
	}

	public String get_hospital_name() {
		return hospital_name;
	}

	public String get_hospital_type() {
		return hospital_type;
	}

	public String get_pkg_code() {
		return pkg_code;
	}

	public String get_pkg_name() {
		return pkg_name;
	}

	public Integer get_pkg_rate() {
		return pkg_rate;
	}

	public String get_id_type() {
		return id_type;
	}

	public String get_id_number() {
		return id_number;
	}

	public String get_district_name() {
		return district_name;
	}

	public long get_aadhar_number() {
		return aadhar_number;
	}

	public String get_policy_year() {
		return policy_year;
	}
	public long get_mobile() {
		return mobile;
	}

	public String get_status() {
		return status;
	}

	public String get_claim_number() {
		return claim_number;
	}

	public String get_gender() {
		return gender;
	}

	public Integer get_age() {
		return age;
	}

}