package weather;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import weather.ipma_client.*;
import java.util.List;

import org.apache.logging.log4j.*;



/**
 * demonstrates the use of the IPMA API for weather forecast
 */
public class WeatherStarter {

    /*
    loggers provide a better alternative to System.out.println
    https://rules.sonarsource.com/java/tag/bad-practice/RSPEC-106
     */
    private static final Logger logger = LogManager.getLogger(WeatherStarter.class.getName());

    public static void  main(String[] args ) {

        String city = args[0];
        int cityId =0;
        /*
        get a retrofit instance, loaded with the GSon lib to convert JSON into objects
         */
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.ipma.pt/open-data/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        
        IpmaService service = retrofit.create(IpmaService.class);
        Call<IpmaRegions> callSync = service.getRegions();
        try {
            Response<IpmaRegions> apiResponse = callSync.execute();
            IpmaRegions ipamRegions = apiResponse.body();
            List<Region> regions = ipamRegions.getData();
            for(Region r : regions){
                if (r.getLocal().equals(city)){
                     cityId = r.getGlobalIdLocal();
                    break;
                }
            }
        } catch (Exception ex) {
            logger.error("Error city code");
            ex.printStackTrace();
        }

        Call<IpmaCityForecast> callSync1 = service.getForecastForACity(cityId);
        try {
            Response<IpmaCityForecast> apiResponse = callSync1.execute();
            IpmaCityForecast forecast = apiResponse.body();
            List<CityForecast> forecast5Days =forecast.getData();
            for (CityForecast cf : forecast5Days){
                if (cf != null) {
                    logger.info(String.format( "Date : %s \nMinimum Temperature: %s Maximum Temperature: %s Precipitation: %s\n",
                    cf.getForecastDate(), cf.getTMin(), cf.getTMax(), cf.getPrecipitaProb()));
                } else {
                    logger.info( "No results!");
                }
            }
        } catch (Exception ex) {
            logger.error("Error forecast 5 days");
            ex.printStackTrace();
        }


    }
}