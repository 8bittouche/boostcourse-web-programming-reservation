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
	public final static Set<String> POSSIBLE_FILE_EXTENSION_SET = Collections.unmodifiableSet(new HashSet<String>(Arrays.asList("png", "jpg", "jpeg")));
}
