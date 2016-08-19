package com.redhat.customer.translate;

import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.camel.Converter;
import org.apache.camel.Exchange;
import org.apache.camel.TypeConversionException;

import com.customer.app.Person;
import com.sun.mdm.index.webservice.CallerInfo;
import com.sun.mdm.index.webservice.ExecuteMatchUpdate;
import com.sun.mdm.index.webservice.PersonBean;
import com.sun.mdm.index.webservice.SystemPerson;

@Converter
public class TransformToExecuteMatch {

	@Converter
	public ExecuteMatchUpdate convertTo(Object value, Exchange e) throws TypeConversionException {

		Person person = (Person) e.getIn().getBody();

		ExecuteMatchUpdate executeMatchUpdate = new ExecuteMatchUpdate();

		CallerInfo callerInfo = new CallerInfo();
		callerInfo.setApplication("xlate-proj");
		callerInfo.setApplicationFunction("");
		callerInfo.setAuthPassword("dleal");
		callerInfo.setAuthUser("dleal");
		callerInfo.setExecutionCycleId(System.currentTimeMillis() + "");
		callerInfo.setSystem(person.getSystemcode());
		callerInfo.setUser("dleal");

		executeMatchUpdate.setCallerInfo(callerInfo);

		SystemPerson systemPerson = new SystemPerson();
		try {
			XMLGregorianCalendar gregCal = DatatypeFactory.newInstance().newXMLGregorianCalendar(new GregorianCalendar());
			systemPerson.setCreateDateTime(gregCal);
		} catch (Exception exc) {
			exc.printStackTrace();
		}
		// systemPerson.setCreateFunction(value);
		// systemPerson.setCreateUser(value);
		systemPerson.setLocalId(person.getLocalid());
		PersonBean personBean = new PersonBean();
		// personBean.setAuthFlag();
		personBean.setBirthOrder(person.getBirthname());
		if (person.getCitizenships().get(0) != null && person.getCitizenships().get(0).getCountry().getCountrycode() != null)
			personBean.setCitizenship(person.getCitizenships().get(0).getCountry().getCountrycode().getDisplaytext());
		if (person.isIsdeceased()) {
			personBean.setDeath(person.getCauseOfDeaths().get(0).getCauseofdeath().getDisplaytext());
			personBean.setDeathCertificate(person.getDeathcertificatenumber());
			personBean.setDod(person.getDateofdeath().getLiteral());
		}
		if (person.getOccupationalHistoryObservations().get(0) != null)
			personBean.setDegree(person.getOccupationalHistoryObservations().get(0).getCurrentoccupation().getDisplaytext());

		personBean.setDistrictOfResidence(person.getDistrictofresidence());
		personBean.setDOB(person.getDateofbirth().getLiteral());
		// personBean.setDriversLicense();
		// personBean.setDriversLicenseExp();
		personBean.setDriversLicenseSt(person.getDriverslicensestate());
		personBean.setEthnic(person.getEthnicity().getDisplaytext());
		personBean.setFatherName(person.getFathername());
		// personBean.setFatherPhoneticCode();
		personBean.setFirstName(person.getBirthname());
		// personBean.setFnamePhoneticCode();
		personBean.setGender(person.getGender().getDisplaytext());
		if (person.getLanguageCapabilities().get(0) != null)
			personBean.setLanguage(person.getLanguageCapabilities().get(0).getLanguage().getDisplaytext());
		personBean.setLastName(person.getLegalname().getFamily());
		// personBean.setLastServiceDate();
		personBean.setLgaCode(person.getLgacode());
		// personBean.setLnamePhoneticCode();
		personBean.setMaiden(person.getMothersmaidenname());
		// personBean.setMaidenPhoneticCode();
		personBean.setMiddleName(person.getLegalname().getMiddle());
		// personBean.setMilitaryBranch();
		personBean.setMotherName(person.getMothername());
		personBean.setMStatus(person.getMaritalstatus().getDisplaytext());
		personBean.setMultipleBirth(person.getMultiplebirthorder().toString());
		// personBean.setNationality();
		if (person.getPersonPensions().get(0) != null) {
			personBean.setPensionExpDate(person.getPersonPensions().get(0).getPensionexpirationdate().toString());
			personBean.setPensionNo(person.getPersonPensions().get(0).getPensionnumber());
		}
		// personBean.setPersonCatCode();
		personBean.setPersonId(person.getIdentifier().getIdentifier());
		// personBean.setPicture(value);
		// personBean.setPobCity();
		// personBean.setPobCountry(value);
		// personBean.setPobState(value);
		personBean.setPrefix(person.getLegalname().getPrefix());
		personBean.setRace(person.getRace().getDisplaytext());
		personBean.setReligion(person.getReligion().getDisplaytext());
		personBean.setRepatriationNo(person.getRepatriationnumber());
		personBean.setSpouseName(person.getSpousename().getGiven());
		personBean.setSSN(person.getSsn().getIdentifier());
		personBean.setSuffix(person.getLegalname().getSuffix());
		// personBean.setTitle();
		// personBean.setVIPFlag();

		systemPerson.setPerson(personBean);

		executeMatchUpdate.setSysObjBean(systemPerson);

		return executeMatchUpdate;
	}

}
