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
        List<DistributedCacheService> Servers = new ArrayList<DistributedCacheService>();
      Servers.add(new DistributedCacheService("http://localhost:3000"));
      Servers.add(new DistributedCacheService("http://localhost:3001"));
      Servers.add(new DistributedCacheService("http://localhost:3002"));

   char[] input ={'0', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j'};
   
   
   //Data put 
   System.out.println("\nNow we add all the data to the required Cache");

   for(int key=1;key<11;key++)
   {
   
   int bucket = Hashing.consistentHash(Hashing.md5().hashInt(key), Servers.size());
   Servers.get(bucket).put(key,Character.toString(input[key]));
   //server.get(bucket).put(putkey, value[putkey]);
   
   System.out.println("PUT --> Key=" + key + " and Value="+ input[key] + " routed to Cache server at localhost://300"+ bucket);
   }

   //Get data
   System.out.println(" ");
   //GET data from Cache servers consistent hashing
  System.out.println("GET data from Cache... ");

  for(int key=1;key<11;key++)
  {
   int bucket=Hashing.consistentHash(Hashing.md5().hashInt(key), Servers.size());
       System.out.println("The key value pair " + key +"-" + Servers.get(bucket).get(key)+ " is received to server " + bucket);
    }
}
    
}
