package com.qsoft.augen.persistence.entity;

import java.util.ArrayList;
import java.util.List;

public class MetaTable
{

	private String nameTable;
	private String nameTableUpperFirst;
	private List<MetaColumn> metaColumns;
	private String namePrimaryKey;
	private String typeDataNamePrimaryKey;

	public MetaTable() {
		setMetaColumns(new ArrayList<MetaColumn>());
	}

	public void setNameTable(String nameTable) {
		this.nameTable = nameTable;
		setNameTableUpperFirst("");
	}

	public String getNameTable() {
		return nameTable;
	}

	public void addMetaColumns(MetaColumn metaColumn) {
		this.getMetaColumns().add(metaColumn);
	}

	public void setNamePrimaryKey(String namePrimaryKey) {
		this.namePrimaryKey = namePrimaryKey;
	}

	public String getNamePrimaryKey() {
		return namePrimaryKey;
	}

	public void setMetaColumns(List<MetaColumn> metaColumns) {
		this.metaColumns = metaColumns;
	}

	public List<MetaColumn> getMetaColumns() {
		return metaColumns;
	}

	public void setNameTableUpperFirst(String nameTableUpperFirst) {
		char[] stringArray = this.nameTable.toCharArray();
		stringArray[0] = Character.toUpperCase(stringArray[0]);
		this.nameTableUpperFirst = new String(stringArray);
	}

	public String getNameTableUpperFirst() {
		return nameTableUpperFirst;
	}

	public void setTypeDataNamePrimaryKey(String typeDataNamePrimaryKey) {
		if (this.typeDataNamePrimaryKey == null) {
			this.typeDataNamePrimaryKey = typeDataNamePrimaryKey;
		}
	}

	public String getTypeDataNamePrimaryKey() {
		return typeDataNamePrimaryKey;
	}
}
