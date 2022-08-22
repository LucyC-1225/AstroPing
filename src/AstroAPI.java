import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;

public class AstroAPI {
    public String makeAPICallPlanet(String date) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://astronomy.p.rapidapi.com/api/v2/bodies/positions?latitude=40.7128&longitude=74.006&from_date=" + date + "&to_date=" + date + "&elevation=166&time=12%3A00%3A00"))
                .header("X-RapidAPI-Key", "05dff7ff84mshfafbe8ce609b5e1p1605a3jsn3543e479ff9f")
                .header("X-RapidAPI-Host", "astronomy.p.rapidapi.com")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = null;
        try {
            response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return response.body();
    }
    //Good JSON parser: http://json.parser.online.fr/
    public ArrayList<String> parseJSONPlanet(String json) {
        ArrayList<String> planetInfo = new ArrayList<String>();
        JSONObject object = new JSONObject(json);
        JSONObject object2 = object.getJSONObject("data");
        JSONObject object3 = object2.getJSONObject("table");
        JSONArray array = object3.getJSONArray("rows");
        for (int i = 0; i < array.length(); i++) {
            JSONObject object4 = array.getJSONObject(i);
            JSONArray array2 = object4.getJSONArray("cells");
            JSONObject nameObject = object4.getJSONObject("entry");
            String name = nameObject.getString("name");
            double distanceFromEarth = array2.getJSONObject(0).getJSONObject("distance").getJSONObject("fromEarth").getDouble("km");
            JSONObject positionObject = array2.getJSONObject(0).getJSONObject("position").getJSONObject("equatorial");
            String rightAscension = positionObject.getJSONObject("rightAscension").getString("string");
            String declination = positionObject.getJSONObject("declination").getString("string");
            JSONObject constellationObject = array2.getJSONObject(0).getJSONObject("position").getJSONObject("constellation");
            String constellation = constellationObject.getString("name");
            //Information is in the format planetName|distance|RA|DC|constellation
            planetInfo.add(name + "|" + distanceFromEarth + "|" + rightAscension + "|" + declination + "|" + constellation);

        }
        return planetInfo;
    }

    public String makeAPICallMoonPhase(String date) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://astronomy.p.rapidapi.com/api/v2/studio/moon-phase"))
                .header("content-type", "application/json")
                .header("X-RapidAPI-Key", "05dff7ff84mshfafbe8ce609b5e1p1605a3jsn3543e479ff9f")
                .header("X-RapidAPI-Host", "astronomy.p.rapidapi.com")
                .method("POST", HttpRequest.BodyPublishers.ofString("{\r\"format\": \"png\",\r\"observer\": {\r\"date\": \"" + date + "\",\r\"latitude\": 40.7128,\r\"longitude\": 74.006\r},\r\"style\": {\r\"backgroundColor\": \"red\",\r\"backgroundStyle\": \"stars\",\r\"headingColor\": \"white\",\r\"moonStyle\": \"sketch\",\r\"textColor\": \"red\"\r},\r\"view\": {\r\"type\": \"portrait-simple\"\r}\r }"))
                .build();
        HttpResponse<String> response = null;
        try {
            response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return response.body();
    }

    public String parseJSONMoonPhase(String json) {
        JSONObject object = new JSONObject(json);
        return object.getJSONObject("data").getString("imageUrl").replace("\\", "");
    }
    //https:\/\/widgets.astronomyapi.com\/moon-phase\/generated\/3fa54eeb0883971850e67da3352bd7fb0e5fc6ce208f9e20d8da71854f88cb9d.png

    public String makeAPICallStarChart(String date) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://astronomy.p.rapidapi.com/api/v2/studio/star-chart"))
                .header("content-type", "application/json")
                .header("X-RapidAPI-Key", "05dff7ff84mshfafbe8ce609b5e1p1605a3jsn3543e479ff9f")
                .header("X-RapidAPI-Host", "astronomy.p.rapidapi.com")
                .method("POST", HttpRequest.BodyPublishers.ofString("{\r\"observer\": {\r\"date\": \"" + date + "\",\r\"latitude\": 40.7128,\r\"longitude\": 74.006\r},\r\"style\": \"navy\",\r\"view\": {\r\"parameters\": {\r\"constellation\": \"ori\"\r},\r\"type\": \"constellation\"\r }\r}"))
                .build();
        HttpResponse<String> response = null;
        try {
            response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return response.body();
    }
    public String parseJSONStarChart(String json) {
        JSONObject object = new JSONObject(json);
        return object.getJSONObject("data").getString("imageUrl").replace("\\", "");
    }
}

/*

{"data":{"observer":{"location":{"longitude":74.006,"elevation":166,"latitude":40.7128}},"dates":{"from":"2022-08-22T12:00:00.000+06:00","to":"2022-08-22T12:00:00.000+06:00"},"table":{"rows":[{"cells":[{"date":"2022-08-22T12:00:00.000+06:00","distance":{"fromEarth":{"km":"151316965.12655","au":"1.01149"}},"position":{"horizontal":{"altitude":{"string":"57° 32' 24\"","degrees":"57.54"},"azimuth":{"string":"148° 19' 12\"","degrees":"148.32"}},"constellation":{"short":"Leo","name":"Leo","id":"leo"},"equatorial":{"rightAscension":{"hours":"10.06","string":"10h 03m 36s"},"declination":{"string":"11° 52' 48\"","degrees":"11.88"}},"horizonal":{"altitude":{"string":"57° 32' 24\"","degrees":"57.54"},"azimuth":{"string":"148° 19' 12\"","degrees":"148.32"}}},"name":"Sun","extraInfo":{"elongation":"0.00000","magnitude":"-26.71724"},"id":"sun"}],"entry":{"name":"Sun","id":"sun"}},{"cells":[{"date":"2022-08-22T12:00:00.000+06:00","distance":{"fromEarth":{"km":"400080.57324","au":"0.00267"}},"position":{"horizontal":{"altitude":{"string":"52° 48' 0\"","degrees":"52.80"},"azimuth":{"string":"261° 14' 24\"","degrees":"261.24"}},"constellation":{"short":"Gem","name":"Gemini","id":"gem"},"equatorial":{"rightAscension":{"hours":"6.15","string":"06h 09m 00s"},"declination":{"string":"26° 43' 12\"","degrees":"26.72"}},"horizonal":{"altitude":{"string":"52° 48' 0\"","degrees":"52.80"},"azimuth":{"string":"261° 14' 24\"","degrees":"261.24"}}},"name":"Moon","extraInfo":{"elongation":"56.50460","magnitude":"-8.45015","phase":{"fraction":"0.052","string":"Waning Crescent","angel":"303.5649"}},"id":"moon"}],"entry":{"name":"Moon","id":"moon"}},{"cells":[{"date":"2022-08-22T12:00:00.000+06:00","distance":{"fromEarth":{"km":"150590575.10193","au":"1.00664"}},"position":{"horizontal":{"altitude":{"string":"35° 10' 48\"","degrees":"35.18"},"azimuth":{"string":"126° 28' 12\"","degrees":"126.47"}},"constellation":{"short":"Vir","name":"Virgo","id":"vir"},"equatorial":{"rightAscension":{"hours":"11.69","string":"11h 41m 24s"},"declination":{"string":"0° 32' 24\"","degrees":"0.54"}},"horizonal":{"altitude":{"string":"35° 10' 48\"","degrees":"35.18"},"azimuth":{"string":"126° 28' 12\"","degrees":"126.47"}}},"name":"Mercury","extraInfo":{"elongation":"26.71792","magnitude":"0.08428"},"id":"mercury"}],"entry":{"name":"Mercury","id":"mercury"}},{"cells":[{"date":"2022-08-22T12:00:00.000+06:00","distance":{"fromEarth":{"km":"243982755.15016","au":"1.63092"}},"position":{"horizontal":{"altitude":{"string":"67° 0' 36\"","degrees":"67.01"},"azimuth":{"string":"177° 32' 24\"","degrees":"177.54"}},"constellation":{"short":"Cnc","name":"Cancer","id":"cnc"},"equatorial":{"rightAscension":{"hours":"9.02","string":"09h 01m 12s"},"declination":{"string":"17° 49' 12\"","degrees":"17.82"}},"horizonal":{"altitude":{"string":"67° 0' 36\"","degrees":"67.01"},"azimuth":{"string":"177° 32' 24\"","degrees":"177.54"}}},"name":"Venus","extraInfo":{"elongation":"16.32500","magnitude":"-3.85223"},"id":"venus"}],"entry":{"name":"Venus","id":"venus"}},{"cells":[{"date":"2022-08-22T12:00:00.000+06:00","distance":{"fromEarth":{"km":"6369.24827","au":"0.00004"}},"position":{"horizontal":{"altitude":{"string":"-90° 11' 24\"","degrees":"-89.81"},"azimuth":{"string":"0° 0' 0\"","degrees":"0.00"}},"constellation":{"short":"Mic","name":"Microscopium","id":"mic"},"equatorial":{"rightAscension":{"hours":"20.94","string":"20h 56m 24s"},"declination":{"string":"-41° 23' 24\"","degrees":"-40.61"}},"horizonal":{"altitude":{"string":"-90° 11' 24\"","degrees":"-89.81"},"azimuth":{"string":"0° 0' 0\"","degrees":"0.00"}}},"name":"Earth","extraInfo":{"elongation":null,"magnitude":null},"id":"earth"}],"entry":{"name":"Earth","id":"earth"}},{"cells":[{"date":"2022-08-22T12:00:00.000+06:00","distance":{"fromEarth":{"km":"151753002.63611","au":"1.01441"}},"position":{"horizontal":{"altitude":{"string":"23° 16' 48\"","degrees":"23.28"},"azimuth":{"string":"275° 33' 36\"","degrees":"275.56"}},"constellation":{"short":"Tau","name":"Taurus","id":"tau"},"equatorial":{"rightAscension":{"hours":"3.93","string":"03h 55m 48s"},"declination":{"string":"18° 53' 24\"","degrees":"18.89"}},"horizonal":{"altitude":{"string":"23° 16' 48\"","degrees":"23.28"},"azimuth":{"string":"275° 33' 36\"","degrees":"275.56"}}},"name":"Mars","extraInfo":{"elongation":"88.03039","magnitude":"-0.01197"},"id":"mars"}],"entry":{"name":"Mars","id":"mars"}},{"cells":[{"date":"2022-08-22T12:00:00.000+06:00","distance":{"fromEarth":{"km":"617434504.18971","au":"4.12729"}},"position":{"horizontal":{"altitude":{"string":"-26° 40' 48\"","degrees":"-25.32"},"azimuth":{"string":"297° 16' 12\"","degrees":"297.27"}},"constellation":{"short":"Cet","name":"Cetus","id":"cet"},"equatorial":{"rightAscension":{"hours":"0.50","string":"00h 30m 00s"},"declination":{"string":"1° 32' 24\"","degrees":"1.54"}},"horizonal":{"altitude":{"string":"-26° 40' 48\"","degrees":"-25.32"},"azimuth":{"string":"297° 16' 12\"","degrees":"297.27"}}},"name":"Jupiter","extraInfo":{"elongation":"141.36578","magnitude":"-2.80883"},"id":"jupiter"}],"entry":{"name":"Jupiter","id":"jupiter"}},{"cells":[{"date":"2022-08-22T12:00:00.000+06:00","distance":{"fromEarth":{"km":"1326222794.38150","au":"8.86525"}},"position":{"horizontal":{"altitude":{"string":"-64° 37' 48\"","degrees":"-63.37"},"azimuth":{"string":"338° 48' 0\"","degrees":"338.80"}},"constellation":{"short":"Cap","name":"Capricornus","id":"cap"},"equatorial":{"rightAscension":{"hours":"21.59","string":"21h 35m 24s"},"declination":{"string":"-16° 16' 12\"","degrees":"-15.73"}},"horizonal":{"altitude":{"string":"-64° 37' 48\"","degrees":"-63.37"},"azimuth":{"string":"338° 48' 0\"","degrees":"338.80"}}},"name":"Saturn","extraInfo":{"elongation":"172.08285","magnitude":"0.17669"},"id":"saturn"}],"entry":{"name":"Saturn","id":"saturn"}},{"cells":[{"date":"2022-08-22T12:00:00.000+06:00","distance":{"fromEarth":{"km":"2915055831.17845","au":"19.48594"}},"position":{"horizontal":{"altitude":{"string":"12° 37' 48\"","degrees":"12.63"},"azimuth":{"string":"281° 51' 36\"","degrees":"281.86"}},"constellation":{"short":"Ari","name":"Aries","id":"ari"},"equatorial":{"rightAscension":{"hours":"3.08","string":"03h 04m 48s"},"declination":{"string":"17° 0' 36\"","degrees":"17.01"}},"horizonal":{"altitude":{"string":"12° 37' 48\"","degrees":"12.63"},"azimuth":{"string":"281° 51' 36\"","degrees":"281.86"}}},"name":"Uranus","extraInfo":{"elongation":"100.22621","magnitude":"5.73676"},"id":"uranus"}],"entry":{"name":"Uranus","id":"uranus"}},{"cells":[{"date":"2022-08-22T12:00:00.000+06:00","distance":{"fromEarth":{"km":"4338606572.62340","au":"29.00179"}},"position":{"horizontal":{"altitude":{"string":"-37° 0' 36\"","degrees":"-36.99"},"azimuth":{"string":"304° 20' 24\"","degrees":"304.34"}},"constellation":{"short":"Aqr","name":"Aquarius","id":"aqr"},"equatorial":{"rightAscension":{"hours":"23.69","string":"23h 41m 24s"},"declination":{"string":"-4° 39' 36\"","degrees":"-3.34"}},"horizonal":{"altitude":{"string":"-37° 0' 36\"","degrees":"-36.99"},"azimuth":{"string":"304° 20' 24\"","degrees":"304.34"}}},"name":"Neptune","extraInfo":{"elongation":"154.40798","magnitude":"7.82156"},"id":"neptune"}],"entry":{"name":"Neptune","id":"neptune"}},{"cells":[{"date":"2022-08-22T12:00:00.000+06:00","distance":{"fromEarth":{"km":"5046141186.70164","au":"33.73137"}},"position":{"horizontal":{"altitude":{"string":"-68° 9' 36\"","degrees":"-67.84"},"azimuth":{"string":"40° 52' 48\"","degrees":"40.88"}},"constellation":{"short":"Sgr","name":"Sagittarius","id":"sgr"},"equatorial":{"rightAscension":{"hours":"19.92","string":"19h 55m 12s"},"declination":{"string":"-24° 58' 48\"","degrees":"-23.02"}},"horizonal":{"altitude":{"string":"-68° 9' 36\"","degrees":"-67.84"},"azimuth":{"string":"40° 52' 48\"","degrees":"40.88"}}},"name":"Pluto","extraInfo":{"elongation":"147.40709","magnitude":"14.37114"},"id":"pluto"}],"entry":{"name":"Pluto","id":"pluto"}}],"header":["2022-08-22T12:00:00.000+06:00"]}},"message":"You're using the demo api key. You may run in to rate limits. Visit astronomyapi.com to get your free API keys."}
 */
