package weather;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import weather.ipma_client.*;
import java.util.logging.Logger;
import java.util.List;



/**
 * demonstrates the use of the IPMA API for weather forecast
 */
public class WeatherStarter {

    /*
    loggers provide a better alternative to System.out.println
    https://rules.sonarsource.com/java/tag/bad-practice/RSPEC-106
     */
    private static final Logger logger = Logger.getLogger(WeatherStarter.class.getName());

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
            ex.printStackTrace();
        }

        Call<IpmaCityForecast> callSync1 = service.getForecastForACity(cityId);
        try {
            Response<IpmaCityForecast> apiResponse = callSync1.execute();
            IpmaCityForecast forecast = apiResponse.body();
            List<CityForecast> forecast5Days =forecast.getData();
            for (CityForecast cf : forecast5Days){
                if (cf != null) {
                    System.out.println(String.format( "Data : %s \nTemperatura minima: %s \nTemperatura máxima: %s \nProb. Precipitação: %s\n",
                    cf.getForecastDate(), cf.getTMin(), cf.getTMax(), cf.getPrecipitaProb()));
                } else {
                    logger.info( "No results!");
                }
                logger.info("ola");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }


    }
}