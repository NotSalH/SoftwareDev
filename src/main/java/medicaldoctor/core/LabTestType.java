package medicaldoctor.core;

public enum LabTestType {
    RED_BLOOD_CELL(LabType.HEMATOLOGIC), WHITE_BLOOD_CELL(LabType.HEMATOLOGIC),
    LIVER_FUNCTION(LabType.HEMATOLOGIC), RENAL_FUNCTION(LabType.HEMATOLOGIC),
    ELECTROLYTE_TEST(LabType.HEMATOLOGIC), URINARY_TEST(LabType.HEMATOLOGIC),
    STOOL_TEST(LabType.HEMATOLOGIC),
    XRAY(LabType.RADIOLOGIC), CT(LabType.RADIOLOGIC), MRI(LabType.RADIOLOGIC);

    private final LabType labType;

    private LabTestType(LabType labType) {
        this.labType = labType;
    }

    public LabType getLabType() {
        return labType;
    }

}
