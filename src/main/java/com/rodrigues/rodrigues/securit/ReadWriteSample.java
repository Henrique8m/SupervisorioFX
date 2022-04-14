package com.rodrigues.rodrigues.securit;

import java.util.Scanner;
import Aladdin.Hasp;
import Aladdin.HaspStatus;

public class ReadWriteSample {

	public void teste() {

		System.out.println("Please Enter the feature you want to login");
		Scanner sc = new Scanner(System.in);
		int feature = sc.nextInt();

		String vendorCode = "AzIceaqfA1hX5wS+M8cGnYh5ceevUnOZIzJBbXFD6dgf3tBkb9cvUF/Tkd/iKu2fsg9wAysYKw7RMAsV"
				+ "vIp4KcXle/v1RaXrLVnNBJ2H2DmrbUMOZbQUFXe698qmJsqNpLXRA367xpZ54i8kC5DTXwDhfxWTOZrB"
				+ "rh5sRKHcoVLumztIQjgWh37AzmSd1bLOfUGI0xjAL9zJWO3fRaeB0NS2KlmoKaVT5Y04zZEc06waU2r6"
				+ "AU2Dc4uipJqJmObqKM+tfNKAS0rZr5IudRiC7pUwnmtaHRe5fgSI8M7yvypvm+13Wm4Gwd4VnYiZvSxf"
				+ "8ImN3ZOG9wEzfyMIlH2+rKPUVHI+igsqla0Wd9m7ZUR9vFotj1uYV0OzG7hX0+huN2E/IdgLDjbiapj1"
				+ "e2fKHrMmGFaIvI6xzzJIQJF9GiRZ7+0jNFLKSyzX/K3JAyFrIPObfwM+y+zAgE1sWcZ1YnuBhICyRHBh"
				+ "aJDKIZL8MywrEfB2yF+R3k9wFG1oN48gSLyfrfEKuB/qgNp+BeTruWUk0AwRE9XVMUuRbjpxa4YA67SK"
				+ "unFEgFGgUfHBeHJTivvUl0u4Dki1UKAT973P+nXy2O0u239If/kRpNUVhMg8kpk7s8i6Arp7l/705/bL"
				+ "Cx4kN5hHHSXIqkiG9tHdeNV8VYo5+72hgaCx3/uVoVLmtvxbOIvo120uTJbuLVTvT8KtsOlb3DxwUrwL"
				+ "zaEMoAQAFk6Q9bNipHxfkRQER4kR7IYTMzSoW5mxh3H9O8Ge5BqVeYMEW36q9wnOYfxOLNw6yQMf8f9s"
				+ "JN4KhZty02xm707S7VEfJJ1KNq7b5pP/3RjE0IKtB2gE6vAPRvRLzEohu0m7q1aUp8wAvSiqjZy7FLaT"
				+ "tLEApXYvLvz6PEJdj4TegCZugj7c8bIOEqLXmloZ6EgVnjQ7/ttys7VFITB3mazzFiyQuKf4J6+b/a/Y";

		Hasp hasp = new Hasp(feature);
		

		hasp.login(vendorCode);

		int status = hasp.getLastError();
		if (HaspStatus.HASP_STATUS_OK != status) {
			System.out.println("Cannot Login !  " + "Error Code:" + status);
			System.out.println("Requested Feature Not Found");
			System.exit(0);
		} else {
			String info;
			String format = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>" + "<haspformat root=\"hasp_info\">"
					+ "    <hasp>" + "        <attribute name=\"id\" />" + "        <attribute name=\"type\" />"
					+ "        <feature>" + "            <attribute name=\"id\" />" + "        </feature>"
					+ "    </hasp>" + "</haspformat>";

			info = hasp.getSessionInfo(format);
			String str = new String(info);
			System.out.print("Successfully logged into the Key");
			System.out.println(str.substring(59, 83));
		}
		System.out.println("---------------------------");
		System.out.println("|Please enter your Choice |");
		System.out.println("---------------------------");
		System.out.println("Press '1' to Read the Data from the Key");
		System.out.println("Press '2' to Write the Data into the Key");
		System.out.println("Press '3' to Logout");
		int ch = sc.nextInt();
		switch (ch) {

		case 1:
			System.out.println("Enter the offset value");
			int off = sc.nextInt();
			System.out.println("Enter the Length");
			int len = sc.nextInt();
			int offset = off;
			byte[] data1 = new byte[len];
			byte[] readdata = new byte[len];
			hasp.read(Hasp.HASP_FILEID_RW, offset, data1);
			for (int i = 0; i < len; i++) {
				readdata[i] = data1[i];

			}

			String str = new String(readdata);
			System.out.print("Data in the Key:");
			System.out.print(str);

			break;

		case 2:
			System.out.println("Enter the offset Value");
			int off1 = sc.nextInt();

			System.out.println("Please enter Data to write in the Key");
			Scanner sc1 = new Scanner(System.in);
			String a = sc1.nextLine();

			byte[] data2 = a.getBytes();
			hasp.write(Hasp.HASP_FILEID_RW, off1, data2);
			int status2 = hasp.getLastError();
			if (HaspStatus.HASP_STATUS_OK != status2)
				System.out.println("Error in writing Data !" + "Error Code: " + status2);
			else
				System.out.println("Data written Successfully! ");

			break;

		case 3:
			hasp.logout();

			int status3 = hasp.getLastError();

			if (HaspStatus.HASP_STATUS_OK != status3)
				System.out.println("Error in Logging out" + "Error Code" + status3);
			else
				System.out.println("Successfully Logged out !");
			break;
		default:
			System.out.println("Wrong Choice entered");

		}
		sc.close();
	}
}
