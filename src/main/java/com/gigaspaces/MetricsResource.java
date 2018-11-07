package com.gigaspaces;

import javax.json.*;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("metrics")
public class MetricsResource {


    @GET
    @Produces("application/json")
    @Path("/")
    public Response get() {
        return Response.ok().build();
    }

    @OPTIONS
    @Path("/annotations")
    public Response annotationOptions() {

        return Response.noContent()
                .header("Access-Control-Allow-Headers", "accept, content-type")
                .header("Access-Control-Allow-Methods", "POST")
                .header("Access-Control-Allow-Origin", "*")
                .build();
    }

    @POST
    @Path("/annotations")
    @Produces("application/json")
    @Consumes("application/json")
    public JsonArray annotation(JsonObject annotation) {

        String sq = annotation.toString();
        System.out.println(sq);

        JsonArrayBuilder arr = Json.createArrayBuilder();
        JsonObjectBuilder obj = Json.createObjectBuilder();
        JsonObject an = annotation.getJsonObject("annotation");
        obj.add("annotation", an);

        Range range = Range.from(annotation.getJsonObject("range"));

        obj.add("time", (range.getDuration() / 2) + range.getStart());
        obj.add("title", "TestTitle");
        obj.add("tags", Json.createArrayBuilder().add("tag1").add("tag2").add("tag3").build());
        obj.add("text", "someText");

        arr.add(obj);
        return arr.build();
    }

    @OPTIONS
    @Path("/search")
    public Response searchOptions() {

        return Response.noContent()
                .header("Access-Control-Allow-Headers", "accept, content-type")
                .header("Access-Control-Allow-Methods", "POST")
                .header("Access-Control-Allow-Origin", "*")
                .build();
    }

    @POST
    @Path("/search")
    //@Consumes("application/json")
    @Produces("application/json")
    public Object search(/*JsonObject query*/) {
        return SpaceDS.getTimeSeriesTypes();
    }

    @OPTIONS
    @Path("/query")
    public Response queryOptions() {

        return Response.noContent()
                .header("Access-Control-Allow-Headers", "accept, content-type")
                .header("Access-Control-Allow-Methods", "POST")
                .header("Access-Control-Allow-Origin", "*")
                .build();
    }


    @GET
    @Path("/test")
    @Produces(MediaType.APPLICATION_JSON)
    public Object ester(){
        System.out.println("Calling test");
        TimeSeriesResults res[] = new TimeSeriesResults[4];

        System.out.println("res:" + res);
        return res;
    }

    @POST
    @Path("/query")
    @Consumes("application/json")
    //@Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Object query(JsonObject query) {

        /*String sq = query1.toString();
        sq = sq.replaceAll("\r?\n", "");
        System.out.println(sq);
        JsonObject query;
        try{
            JsonReader reader = Json.createReader(new StringReader(sq));
            query  = reader.readObject();
            reader.close();
        }
        catch(Exception e){
            return "got exception reading json: " + e.toString()+ "trace:" + e.getStackTrace().toString();
        }*/

        Range range = Range.from(query.getJsonObject("range"));

        JsonArray targets = query.getJsonArray("targets");
        if (targets != null) {
            JsonObjectBuilder target = Json.createObjectBuilder();
            String tgt = targets.getJsonObject(0).getString("target");
            String type = targets.getJsonObject(0).getString("type");
            if (type.equals("table")) {
                Object[] arr = new Object[1];
                arr[0] = getTableResults(tgt, range);
                return arr;
            } else {
                return getTimeSeriesResults(tgt, range);
            }
        } else {
            return Json.createArrayBuilder().build();
        }
    }



    public Object getTableResults(String type, Range range){
        System.out.println("getTableResults:Query target is:" + type);
        return SpaceDS.getTableResults(type, range);
    }

    public Object getTimeSeriesResults(String type, Range range){
        System.out.println("getTimeSeriesResults:Query target is:" + type);
        return SpaceDS.getResultsAsTimeSeries(type, range);
    }

}
