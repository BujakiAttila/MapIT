package com.github.mapit;

import java.io.IOException;

import com.github.mapit.backend.IMapITApp;
import com.github.mapit.backend.ImageLoader;
import com.github.mapit.server.IServer;

/**
 * Main class.
 * 
 * @author Attila
 */
public class MapIT {
	public static void main(String [] args) throws IOException
	{
		System.out.println("Started.");
		IServer server = null;
		try
		{
			// Create the main components.
			MapITFactory factory = new MapITFactory();
			ImageLoader loader = factory.getImageLoader();
			IMapITApp app = factory.getApp(loader);
			server = factory.getServer(app);
			
			// Start the components.
			server.init();
			app.init();
			
			// Run until user interruption.
			System.in.read();
		}
		catch(Exception ex)
		{
			System.out.println("Exception:" + ex);			
		}
		finally
		{			
			// Free resources.
			if(server != null)
			{
				System.out.println("Kill the server.");
				server.kill();
			}
		}
		System.out.println("Stopped.");
	}
}
