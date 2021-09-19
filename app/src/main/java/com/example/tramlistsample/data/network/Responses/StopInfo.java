package com.example.tramlistsample.data.network.Responses;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(name = "stopInfo")
public class StopInfo {
    @Element(name = "message")
    public String message;
    @ElementList(entry = "direction",type = DirectionModel.class,inline = true)
    public List<DirectionModel> direction;
//    @ElementList( entry= "direction",required = false)
//    public Direction direction;
    @Attribute(name = "created")
    public String created;
    @Attribute(name = "stop")
    public String stop;
    @Attribute(name = "stopAbv")
    public String stopAbv;

}
