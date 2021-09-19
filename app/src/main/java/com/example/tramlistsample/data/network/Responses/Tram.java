package com.example.tramlistsample.data.network.Responses;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

@Root(name = "tram")
public class Tram {
    @Attribute(name = "dueMins")
    public String dueMins;
    @Attribute(name = "destination")
    public String destination;
}
