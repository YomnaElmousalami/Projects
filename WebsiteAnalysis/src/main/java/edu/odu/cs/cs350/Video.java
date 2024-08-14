package edu.odu.cs.cs350;

//citation: https://www.cs.odu.edu/~tkennedy/cs330/latest/Public/designWebsiteAnalysis00/
/**
 * This class is an extension of the anchor class and is used to identify and classify videos
 * 
 * @author yomnaE1
 */
public class Video extends Anchor
{
    /**
     * Initializes an empty video
     */
    public Video()
    {
        this.typeOfResourceKind = ResourceKind.VIDEO;
    }

}