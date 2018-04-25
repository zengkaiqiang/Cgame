package com.weile.casualgames.utils;

import android.os.Environment;
import android.os.StatFs;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.StringTokenizer;


public class StorageUtils {

	public static class StorageInfo {

		public final String path;
		public final boolean readonly;
		public final boolean removable;
		public final int number;
		public long size;

		StorageInfo(String path, boolean readonly, boolean removable, int number) {
			this.path = path;
			this.readonly = readonly;
			this.removable = removable;
			this.number = number;
		}

		public String getDisplayName() {
			StringBuilder res = new StringBuilder();
			if (!removable) {
				res.append("Internal SD card");
			} else if (number > 1) {
				res.append("SD card " + number);
			} else {
				res.append("SD card");
			}
			if (readonly) {
				res.append(" (Read only)");
			}
			return res.toString();
		}

		@Override
		public String toString() {
			return "StorageInfo [path=" + path + ", readonly=" + readonly + ", removable=" + removable + ", number="
					+ number + ", size=" + size + "]";
		}

	}

	/**
	 * 内置存储
	 * 
	 * @return
	 */
	public static StorageInfo getInternalStorage() {
		StorageInfo info = null;
		String def_path = Environment.getExternalStorageDirectory().getPath();
		boolean def_path_removable = true;
		String def_path_state = Environment.getExternalStorageState();
		boolean def_path_available = def_path_state.equals(Environment.MEDIA_MOUNTED)
				|| def_path_state.equals(Environment.MEDIA_MOUNTED_READ_ONLY);
		boolean def_path_readonly = Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED_READ_ONLY);
		int cur_removable_number = 1;

		if (def_path_available) {
			info = new StorageInfo(def_path, def_path_readonly, def_path_removable,
					def_path_removable ? cur_removable_number++ : -1);
			info.size = getStorageSize(info.path);
		}

		return info;
	}

	/**
	 * 扩展存储
	 * 
	 * @return
	 */
	public static StorageInfo getExternalStorage() {
		List<StorageInfo> list = getStorageList();
		return list.size() >= 2 ? list.get(1) : null;
	}

	/**
	 * 获得存储列表
	 * 
	 * @return
	 */
	public static List<StorageInfo> getStorageList() {

		List<StorageInfo> list = new ArrayList<StorageInfo>();
		String def_path = Environment.getExternalStorageDirectory().getPath();
		boolean def_path_removable = true;
		String def_path_state = Environment.getExternalStorageState();
		boolean def_path_available = def_path_state.equals(Environment.MEDIA_MOUNTED)
				|| def_path_state.equals(Environment.MEDIA_MOUNTED_READ_ONLY);
		boolean def_path_readonly = Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED_READ_ONLY);

		HashSet<String> paths = new HashSet<String>();
		int cur_removable_number = 1;

		if (def_path_available) {
			paths.add(def_path);
			StorageInfo info = new StorageInfo(def_path, def_path_readonly, def_path_removable,
					def_path_removable ? cur_removable_number++ : -1);
			info.size = getStorageSize(info.path);
			list.add(0, info);
		}

		BufferedReader buf_reader = null;
		try {
			buf_reader = new BufferedReader(new FileReader("/proc/mounts"));
			String line;
			while ((line = buf_reader.readLine()) != null) {
				if (line.contains("vfat") || line.contains("/mnt")) {
					StringTokenizer tokens = new StringTokenizer(line, " ");
					tokens.nextToken(); // device
					String mount_point = tokens.nextToken(); // mount point
					if (paths.contains(mount_point)) {
						continue;
					}
					tokens.nextToken(); // file system
					List<String> flags = Arrays.asList(tokens.nextToken().split(",")); // flags
					boolean readonly = flags.contains("ro");

					if (line.contains("/dev/block/vold")) {
						if (!line.contains("/mnt/secure") && !line.contains("/mnt/asec") && !line.contains("/mnt/obb")
								&& !line.contains("/dev/mapper") && !line.contains("tmpfs")) {
							paths.add(mount_point);
							StorageInfo info = new StorageInfo(mount_point, readonly, true, cur_removable_number++);
							info.size = getStorageSize(info.path);
							list.add(info);
						}
					}
				}
			}

		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (buf_reader != null) {
				try {
					buf_reader.close();
				} catch (IOException ex) {
				}
			}
		}
		return list;
	}

	/**
	 * 获得存储大小，单位byte
	 * 
	 * @param path
	 * @return
	 */
	public static long getStorageSize(String path) {
		try {
			StatFs fs = new StatFs(path);
			return (long)fs.getAvailableBlocks() * (long)fs.getBlockSize();
		} catch (Exception e) {
		}
		return 0L;
	}
}