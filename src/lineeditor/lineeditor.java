package lineeditor;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class lineeditor {
	
	private List<String> lines;
	private int len;
	
	private void readFile(String path)
	{
		lines = new ArrayList<String>();
		len = 0;
		try {
			BufferedReader br = new BufferedReader(new FileReader(path));
			String line = br.readLine();
			while (line != null)
			{
				lines.add(line);
				len++;
				line = br.readLine();
			}
			br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	private void listLines()
	{
		for (int i=0; i<len; i++)
		{
			System.out.println((i+1) + ": " + lines.get(i));
		}
	}
	
	private boolean delete(int n)
	{
		if(n>len || n<=0)
			return false;
		lines.remove(n-1);
		len--;
		return true;
	}
	
	private boolean insert(int n, String line)
	{
		if (n>=len+1 || n<=0)
			return false;
		lines.add(n-1, line);
		len++;
		return true;
	}
	
	private boolean saveFile(String path)
	{
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(path));
			bw.write("");
			for (int i=0; i<len; i++)
			{
				if (i!=0)
					bw.append("\n");
				bw.append(lines.get(i));
			}
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public static void main(String[] args) throws IOException
	{
		if (args.length == 0)
		{
			throw new FileNotFoundException("File path not entered.");
		}
		//System.out.println("path: " + args[0]);
		lineeditor ob = new lineeditor();
		String path = args[0];
		
		ob.readFile(path);
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while (true)
		{
			String cmd = br.readLine();
			String[] input = cmd.split(" ");
			if (input[0].equalsIgnoreCase("list"))
				ob.listLines();
			else if (input[0].equalsIgnoreCase("del"))
				ob.delete(Integer.parseInt(input[1]));
			else if (input[0].equalsIgnoreCase("ins"))
			{
				String str = br.readLine();
				ob.insert(Integer.parseInt(input[1]), str);
			}
			else if (input[0].equalsIgnoreCase("save"))
				ob.saveFile(path);
			else if (input[0].equalsIgnoreCase("quit"))
				break;
			else
				continue;
		}
	}

}
