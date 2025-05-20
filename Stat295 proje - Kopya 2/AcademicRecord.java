package model;

public abstract class AcademicRecord {
    protected String recordType;

    public AcademicRecord(String recordType) {
        this.recordType = recordType;
    }

    public abstract void printSummary();

    public String getRecordType() {
        return recordType;
    }

    public void setRecordType(String recordType) {
        this.recordType = recordType;
    }
}

