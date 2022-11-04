//� A+ Computer Science  -  www.apluscompsci.com
//Name -
//Date -
//Class - 
//Lab  -


// You do not need to make any changes to this runner file!

public class Musician
{
 // cyclicAirAmount = the amount of air within one air cycle. cyclicAirBurst refers to the unique initial burst of air whenever starting a new air cycle. cABT therefore represents the length of the burst and cABF the initial burst force that should be applied. The initial force will be automatically decayed to the standard airForce.
 private double embouchureMetric, uniqueMusicianKey, airForce, airSpeed, cyclicAirAmount, cyclicAirBurstTime, cyclicAirBurstForce; // What metric(s) should embouchure be based on? embQuality? Or specific aspects (e.g. embBass...). The UMK is unique to every musician and helps add a slight unique touch to each musician.
 private boolean isCircularBreathing;
 private String location;
 // private double... *** LOCATION METRICS GO HERE??

 private double distractionResistance, environmentResistance; // dR is a "shield" against envDistractionIndex (reduces or subtracts some from it). eR shields against environmental influence (not just distractions).


 /* // don't use please
 public static void main( String args[] )
 {
  int[] cases = {234, 10000, 111, 9005, 84645, 8547, 123456789, 55556468, 8525455, 8514548, 111111, 1212121212, 222222 };
  
  Digitridoo s = new Digitridoo();
    
  for( int val : cases )
  {
   System.out.println( s.go( val ) );
  }
 }*/
}