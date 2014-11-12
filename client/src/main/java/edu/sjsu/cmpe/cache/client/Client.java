package edu.sjsu.cmpe.cache.client;

import java.util.ArrayList;
import java.util.List;

import com.google.common.hash.Hashing;

public class Client {
    public static void main(String[] args) throws Exception {
        System.out.println("Starting Cache Client...");
        /*CacheServiceInterface cache = new DistributedCacheService("http://localhost:3000");
        cache.put(1, "foo");
        System.out.println("put(1 => foo)");
        String value = cache.get(1);
        System.out.println("get(1) => " + value);
        System.out.println("Existing Cache Client...");*/
       //adding all servers
       List<CacheServiceInterface> Servers = new ArrayList<CacheServiceInterface>();
      Servers.add(new DistributedCacheService("http://localhost:3000"));
      Servers.add(new DistributedCacheService("http://localhost:3001"));
      Servers.add(new DistributedCacheService("http://localhost:3002"));

   char[] input ={'0', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j'};
   
   
   //Data put 
   System.out.println("\nNow we add all the data to the required Cache");

   for(int key=1;key<=11;key++)
   {
   
   int bucket = Hashing.consistentHash(Hashing.md5().hashString(Integer.toString(key)), Servers.size());
   Servers.get(bucket).put(key,Character.toString(input[key]));
   
   System.out.println("PUT --> Key=" + key + " and Value="+ input[key] + " routed to Cache server at localhost://300"+ bucket);
   }

   //Get data
   System.out.println(" ");
   //GET data from Cache servers consistent hashing
  System.out.println("GET data from Cache... ");

  for(int key=1;key<input.length-1;key++)
  {
   int bucket=Hashing.consistentHash(Hashing.md5().hashString(Integer.toString(key)), Servers.size());
   Servers.get(bucket);
   //String value = serverToGetData.get(key);
  System.out.println("GET --> Obtained Key=" + key + " and Value="+ value + " from Cache server at localhost://300"+ bucket);
  }
   
   
   

    }
}
