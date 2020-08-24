package airtiesapp;
public class uniqfile {
    
    private String Uniqid;
    private String Information;
    private String PageLink;
    private String PartialObject;
    private String PageObject;
    private String ObjectType;
    private String SaveButton;
    
    /**
     *
     * @param Uniqid
     * @param Information
     * @param PageLink
     * @param PartialObject
     * @param PageObject
     * @param ObjectType
     * @param SaveButton
     */
    public uniqfile(String Uniqid,String Information,String PageLink,String PartialObject,String PageObject,String ObjectType,String SaveButton){
    
        this.Uniqid = Uniqid;
        this.Information = Information;
        this.PageLink = PageLink;
        this.PartialObject = PartialObject;
        this.PageObject = PageObject;
        this.ObjectType = ObjectType;
        this.SaveButton = SaveButton;
    }

    /**
     * @return the Uniqid
     */
    public String getUniqid() {
        return Uniqid;
    }

    /**
     * @param Uniqid the Uniqid to set
     */
    public void setUniqid(String Uniqid) {
        this.Uniqid = Uniqid;
    }

    /**
     * @return the Information
     */
    public String getInformation() {
        return Information;
    }

    /**
     * @param Information the Information to set
     */
    public void setInformation(String Information) {
        this.Information = Information;
    }

    /**
     * @return the PageLink
     */
    public String getPageLink() {
        return PageLink;
    }

    /**
     * @param PageLink the PageLink to set
     */
    public void setPageLink(String PageLink) {
        this.PageLink = PageLink;
    }

    /**
     * @return the PartialObject
     */
    public String getPartialObject() {
        return PartialObject;
    }

    /**
     * @param PartialObject the PartialObject to set
     */
    public void setPartialObject(String PartialObject) {
        this.PartialObject = PartialObject;
    }

    /**
     * @return the PageObject
     */
    public String getPageObject() {
        return PageObject;
    }

    /**
     * @param PageObject the PageObject to set
     */
    public void setPageObject(String PageObject) {
        this.PageObject = PageObject;
    }

    /**
     * @return the ObjectType
     */
    public String getObjectType() {
        return ObjectType;
    }

    /**
     * @param ObjectType the ObjectType to set
     */
    public void setObjectType(String ObjectType) {
        this.ObjectType = ObjectType;
    }

    /**
     * @return the SaveButton
     */
    public String getSaveButton() {
        return SaveButton;
    }

    /**
     * @param SaveButton the SaveButton to set
     */
    public void setSaveButton(String SaveButton) {
        this.SaveButton = SaveButton;
    }
    
}
