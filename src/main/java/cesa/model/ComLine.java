package cesa.model;

import java.util.HashMap;

/**
 * DAO which represents a communications line
 */
public class ComLine {
    /**
     * @param id represents the number assigned to a communications line.
     * @param portDir is the OS serial port direction.
     * @param heliostats HashMap which contains <code>Heliostat</code> objects within a <code>ComLine</code>.
     */
    private int id;
    private String portDir;
    private HashMap<Integer, Heliostat> heliostats;

    public ComLine() {
        this(0, null, null);
    }

    public ComLine(int id, String portDir, HashMap<Integer, Heliostat> heliostats) {
        this.id = id;
        this.portDir = portDir;
        this.heliostats = heliostats;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPortDir() {
        return portDir;
    }

    public void setPortDir(String portDir) {
        this.portDir = portDir;
    }

    public HashMap<Integer, Heliostat> getHeliostats() {
        return heliostats;
    }

    public void setHeliostats(HashMap<Integer, Heliostat> heliostats) {
        this.heliostats = heliostats;
    }

    public void setAttributes(ComLine apiCache) {
        this.id = apiCache.getId();
        this.portDir = apiCache.getPortDir();
        this.heliostats = apiCache.getHeliostats();
    }
}