import java.io.BufferedReader;
import java.io.FileReader;
import java.security.MessageDigest;

public class BruteCrack
{
    MessageDigest md;

    char min_char_value;
    char max_char_value;

    char[] guess;

    int max_num_chars;

    private static String fileName = "Users.txt";

    public BruteCrack() throws Exception
    {
        min_char_value = 32;
        max_char_value = 126;
        max_num_chars = 10;

        md = MessageDigest.getInstance("MD5");

        guess = null;
    }

    public String crack(String hash)
    {
        boolean done = false;
        String guess_hash;

        for(int num_chars = 0; num_chars < max_num_chars && !done; num_chars++)
        {
            // Initialize guess at the start of each interation
            guess = new char[num_chars];
            for(int x = 0; x < num_chars; x++)
            {
                guess[x] = min_char_value;
            }

            while(canIncrementGuess() && !done)
            {
                incrementGuess();
                md.reset();
                md.update(new String(guess).getBytes());
                guess_hash = hashToString(md.digest());

                if(hash.equals(guess_hash))
                {
                    done = true;
                }
            }
        }
        return new String(guess);
    }

    protected boolean canIncrementGuess()
    {
        boolean canIncrement = false;

        for(int x=0; x < guess.length; x++)
        {
            if(guess[x] < max_char_value) canIncrement = true;
        }
        return canIncrement;
    }

    protected void incrementGuess()
    {
        boolean incremented = false;

        for(int x = (guess.length - 1);x >= 0 && !incremented; x--)
        {
            if(guess[x] < max_char_value)
            {
                guess[x]++;
                if(x < (guess.length-1))
                {
                    guess[x+1] = min_char_value;
                }
                incremented = true;
            }
        }
    }

    protected String hashToString(byte[] hash)
    {
        StringBuffer sb = new StringBuffer();

        for(int i = 0; i < hash.length; i++)
        {
            sb.append(Integer.toString((hash[i] & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString();
    }

    public static String Crack(String hash) {
        try
        {
            BruteCrack bc = new BruteCrack();
            long start;
            long end;
            String answer;

            start = System.nanoTime();
            answer = bc.crack(hash);
            end = System.nanoTime();

            System.out.println("Processing Time: " + ((end - start)/1000000000));

            return answer;
        }
        catch(Exception e)
        {
            System.out.println("Exception: " + e.toString());
        }
        return "Something went Wrong";
    }

    public static void CrackAllUsers() throws Exception{
        FileReader fileReader = new FileReader(fileName);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        String st;
        for(int i = 0; i < 4;i++){
            if((st = bufferedReader.readLine()) != null){
                String pass = BruteCrack.Crack(st.substring(5));
                System.out.println("Usuario: " + st.substring(0,4) + "\n Senha: " + pass);
            }
        }
        bufferedReader.close();
    }
}