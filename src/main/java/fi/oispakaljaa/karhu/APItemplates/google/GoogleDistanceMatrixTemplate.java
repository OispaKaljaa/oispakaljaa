package fi.oispakaljaa.karhu.APItemplates.google;

/**
 * Created by Hugo on 9.12.2016.
 */

public class GoogleDistanceMatrixTemplate {
    public GoogleDistanceMatrixTemplate() {}
    public String[] destination_addresses;
    public String[] origin_addresses;
    public ElementRes[] rows;
    public String status;
}