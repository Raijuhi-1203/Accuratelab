package digitalbull.app.accuratelab.Utils;


public class TempModel {
    private String DoctorName;
    private int CSTID;
    private String WorkingWith;
    private int RID;
    private String Area;
    private int AreaID;
    private String Chemist;
    private boolean MChemist;
    private String dcrdate;
    public String getDoctorName() {
        return DoctorName;
    }

    public void setDoctorName(String doctorName) {
        DoctorName = doctorName;
    }

    public int getCSTID() {
        return CSTID;
    }

    public void setCSTID(int CSTID) {
        this.CSTID = CSTID;
    }

    public String getWorkingWith() {
        return WorkingWith;
    }

    public void setWorkingWith(String workingWith) {
        WorkingWith = workingWith;
    }

    public int getRID() {
        return RID;
    }

    public void setRID(int RID) {
        this.RID = RID;
    }

    public String getArea() {
        return Area;
    }

    public void setArea(String area) {
        Area = area;
    }

    public int getAreaID() {
        return AreaID;
    }

    public void setAreaID(int areaID) {
        AreaID = areaID;
    }

    public String getChemist() {
        return Chemist;
    }

    public void setChemist(String chemist) {
        Chemist = chemist;
    }

    public boolean isMChemist() {
        return MChemist;
    }

    public void setMChemist(boolean MChemist) {
        this.MChemist = MChemist;
    }

    public String getDcrdate() {
        return dcrdate;
    }

    public void setDcrdate(String dcrdate) {
        this.dcrdate = dcrdate;
    }
}
