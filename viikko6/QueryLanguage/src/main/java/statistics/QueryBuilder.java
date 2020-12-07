package statistics;

import java.util.ArrayList;
import matcher.*;

public class QueryBuilder 
{
    private ArrayList<Matcher> matchers;
    QueryBuilder() {}
    
    public void playsIn(string name) 
    {
        matchers.add(new playsIn(name));
    }

    public void all()
    {
        matchers.add(new All());
    }

    public void And(Matchers... match)
    {
        matchers.add(new And(match));
    }    
}