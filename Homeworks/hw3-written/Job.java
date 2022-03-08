/**
 * @author Derek Garcia
 **/

public class Job {

    private final int ID;
    private final int pTime;
    private int fTime;

    public Job(int ID, int pTime){
        this.ID = ID;
        this.pTime = pTime;
    }

    public int getID() {
        return ID;
    }

    public int getpTime() {
        return pTime;
    }

    public int getfTime() {
        return fTime;
    }

    public void setfTime(int fTime){
        this.fTime = fTime;
    }

    @Override
    public String toString(){

        return this.ID + "\n- pTime: " + this.pTime + "\n- fTime: " + this.fTime;

    }
}
