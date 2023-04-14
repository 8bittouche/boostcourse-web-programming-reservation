package kr.or.connect.reservation.constant;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Constant {
	public static final String ROOT_FOLDER = "c:/tmp/";
	public static final int LIMIT_DISPLAY_NUM = 4;
	public static final int TOTAL_LIST_ID = 0;
	public static final int REVIEW_MAX_LENGTH = 400;
	public static final Set<String> POSSIBLE_FILE_EXTENSION_SET = Collections.unmodifiableSet(new HashSet<String>(Arrays.asList("png", "jpg", "jpeg")));
	public static final int CACHE_PERIOD = 31556926;
	public static final int MAX_UPLOAD_SIZE = 10485760; // 1024 * 1024 * 10
}
