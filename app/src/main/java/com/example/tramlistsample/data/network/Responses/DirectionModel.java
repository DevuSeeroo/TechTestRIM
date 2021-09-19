package com.example.tramlistsample.data.network.Responses;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(name = "direction",strict = false)
public class DirectionModel {
    @Attribute(name = "name",required = false)
    public String name;
//    @Attribute(name = "dueMins",required = false)
//    private String dueMins;
//    @Attribute(name = "destination")
//    private String destination;
    @ElementList(name = "tram",inline = true,required = false)
    public List<Tram> tram;

}
