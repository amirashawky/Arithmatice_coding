import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;


public class arithmatic {
	
	private static void Sort(ArrayList<Character> data_characters , ArrayList<Double> counter)
	{
		boolean swap=true;
		char temp1;
		double temp2;
		do
		{
			swap=false;
			for(int i=0;i<data_characters.size()-1;i++)
			{
				if(data_characters.get(i)>data_characters.get(i+1))
				{
					temp1=data_characters.get(i);
					data_characters.set(i, data_characters.get(i+1));
					data_characters.set(i+1,temp1);
					
					temp2=counter.get(i);
					counter.set(i, counter.get(i+1));
					counter.set(i+1,temp2);
					swap=true;
				}
			}
			
		}
		while(swap);
		
	}
	public static String read_from_file(String file)
	{
		String text = "" ; 
	    try 
	    {
	 	   FileInputStream inputfile = null ;
	 	   
	 	   inputfile = new FileInputStream(file);
	 	   int character;
			   while ((character = inputfile.read()) != -1)
			   {
				text += (char)character ;
			   }
			   System.out.println(inputfile.read());

	 	} 
	 catch (IOException e)
	 {
			e.printStackTrace();
		}
	   return text ;
	}                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                       
	public static void Compress(String data) throws FileNotFoundException
	{
		
		
		double lower = 0.0;
		double upper = 0.0;
		ArrayList<yoursymbols > Symbols=new ArrayList<yoursymbols>();
		yoursymbols  temp = new yoursymbols();
		char x ;
		boolean found;
		
///////////////////////////////////// to construct the nodes data /////////
		ArrayList<Double> counter=new ArrayList<Double>() ;
		ArrayList<Character> data_characters=new ArrayList<Character>() ;
		double newcount;
		int index ;
		for(int i=0;i<data.length();i++)  
		{
			
			index=data_characters.indexOf(data.charAt(i));
			if(index==-1)
			{
				data_characters.add(data.charAt(i));
				counter.add((double) 1);
			}
			else
			{
				newcount=counter.get(index)+1;
				counter.set(index, (double) newcount);
			}
		}
		
		Sort(data_characters,counter);    /// Sort Function //////
		
		double prob;
		
		for(int i=0;i<counter.size();i++)
		{
			prob=counter.get(i)/data.length();
			counter.set(i, prob);
		}
		
//		System.out.println(data_characters);
//		System.out.println(counter);
		
///////////////////////// write characters and probability ///////////////////
		
		PrintWriter out=new PrintWriter("Characters.txt");
		 for(int i=0;i<data_characters.size();i++)
	       {
	    	  out.println(data_characters.get(i));
	    	  out.println(counter.get(i));
	       }
	       out.close();
/////////////////////////////////////////////////////////////////////////////
	       
		yoursymbols y ;
		double low=0.0;
		for(int i=0;i<data_characters.size();i++)
		{
			y=new yoursymbols();
			y.symbol=data_characters.get(i);
			y.low_range_of_symbol=low;
			y.high_range_of_symbol=low+counter.get(i);
			y.lower=y.low_range_of_symbol;
			y.upper=y.high_range_of_symbol;
			low=y.high_range_of_symbol;
			Symbols.add(y);
			
		}
		
		
		
		

//////////////////////////////////// to compress the text ///////////

	    for(int i=0 ;i<data.length();i++)
	    {
	    	 x=data.charAt(i);
	    	found=false;
	    	for(int j=0;(j<Symbols.size())&&(found==false);j++)
	    	{
	    		if(  Symbols.get(j).symbol ==x)
	    		{
	    		   found=true;
	    		   lower=Symbols.get(j).lower;
	    		   upper=Symbols.get(j).upper;
	    		}
	    		
	    	}
	    	
	    	if(found==false)
	    	{
	    		System.out.println("can't fined the Symbol. "+x+"  ");
	    	}
	    	else
	    	{
	    	    for(int k=0;k<Symbols.size();k++)
	    	    {
	    	    	temp=new yoursymbols();
	    	    	temp.symbol=Symbols.get(k).symbol;
	    	    	temp.high_range_of_symbol=Symbols.get(k).high_range_of_symbol;
		    	    temp.low_range_of_symbol=Symbols.get(k).low_range_of_symbol ;
		    	      
	    	      temp.lower=lower+( (upper-lower) * Symbols.get(k).low_range_of_symbol );
	    	      temp.upper=lower+( (upper-lower) * Symbols.get(k).high_range_of_symbol );
	    	    
	    	      Symbols.set(k, temp);
	    	    }
	    	}
	    	
	    }
	   
		double CodeWord=((double) (Math.random()*(upper - lower))) + lower;
		System.out.println("The Code Word Is  : "+ CodeWord);
		 
//			if(CodeWord>lower)
//			System.out.println("t ");
//		if(CodeWord<upper)
//			System.out.println("t ");

////////////////////// write the code word in the file ///////////////////////
		out=new PrintWriter("Code_word_file.txt");
	    	  out.println(CodeWord);
	    	  out.println(data.length());
	    	  	       out.close();
		

				
	}
	
	public static void De_Compress() throws IOException
	{
		ArrayList<yoursymbols> q =new ArrayList<yoursymbols>();
		int numbers_of_characters ; //// read numbers_of_characters from the file 
		double code_word ;          /// read code_word from the file 
		boolean found;
		double lower = 0;
		double upper = 0;
		String decoded_message ="";
		yoursymbols temp;
		
		
//////////////////// Read The data from the file ////////////////////
		BufferedReader br = null ;
		br  = new BufferedReader ( new FileReader ("Characters.txt"));
		
		ArrayList<Character> character=new ArrayList<Character>();
		ArrayList<Double> probability=new ArrayList<Double>();

		
		String x ="";
		  x = br.readLine () ;
		while( x != null)
		{
			character.add(x.charAt(0))	;
			x = br.readLine () ;
			probability.add(Double.parseDouble(x))	;
			x = br.readLine () ;
			
		}
  br.close();
 
		yoursymbols y ;
		double low=0.0;
		for(int i=0;i<character.size();i++)
		{
			y=new yoursymbols();
			y.symbol=character.get(i);
			y.low_range_of_symbol=low;
			y.high_range_of_symbol=low+probability.get(i);
			y.lower=y.low_range_of_symbol;
			y.upper=y.high_range_of_symbol;
			low=y.high_range_of_symbol;
			q.add(y);
			
		}
		
///////////////////// Read the code word from the file //////////////
		
		br  = new BufferedReader ( new FileReader ("Code_word_file.txt"));
		x = br.readLine () ;
		code_word=Double.parseDouble(x);
		x = br.readLine () ;
		numbers_of_characters=Integer.parseInt(x);
/////////////////////////// to de_compress the message /////////////////
		for(int i=0;i<numbers_of_characters;i++)
		{
			found=false;
			for(int j=0;(j<q.size())&&(found==false);j++)
			{

				if((code_word> q.get(j).low_range_of_symbol )&&(code_word<q.get(j).high_range_of_symbol))
				{
					found=true;
					decoded_message=decoded_message+q.get(j).symbol;
					lower=q.get(j).lower;
		    		upper=q.get(j).upper;
		    		code_word=((code_word-q.get(j).low_range_of_symbol)/(q.get(j).high_range_of_symbol-q.get(j).low_range_of_symbol)) ;
				}
			}
			
			if(found==false)
			{
				System.out.println("Not in the range . ");
			}
			
		else
		{
    	    for(int k=0;k<q.size();k++)
    	    {
    	    	temp=new yoursymbols();
    	    	temp.symbol=q.get(k).symbol;
    	    	temp.high_range_of_symbol=q.get(k).high_range_of_symbol;
	    	    temp.low_range_of_symbol=q.get(k).low_range_of_symbol ;
	    	      
    	      temp.lower=lower+( (upper-lower) * q.get(k).low_range_of_symbol );
    	      temp.upper=lower+( (upper-lower) * q.get(k).high_range_of_symbol );
    	      q.set(k, temp);
    	    }
  	     

    	}
			
	}
			
		System.out.println("the decoded_message is : " + decoded_message);	
	}
		
	
	public static void main(String[] args) throws IOException
	{
		// TODO Auto-generated method stub
	 System.out.println("The Code Before Compress Is : " + "Aya" );
          Compress("Aya");
         System.out.println("After De_Compress :  ");
         De_Compress();
         
      
    
	}

}
