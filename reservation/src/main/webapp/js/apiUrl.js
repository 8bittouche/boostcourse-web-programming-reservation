const urlSchema = "http://";
const portNum = 8080;
const hostName = "localhost" + ":" + portNum;
const projectName = "/reservation";
const promotionApi = "/api/promotions";
const categoryApi = "/api/categories";
const productApi = "/api/products";
const reservationApi = "/api/reservations";
const imageDownload = "/download";

const productApiUri = urlSchema + hostName + projectName + productApi;
const promotionApiUri = urlSchema + hostName + projectName + promotionApi;
const categoryApiUri = urlSchema + hostName + projectName + categoryApi;
const displayInfoApiUri = urlSchema + hostName + projectName + productApi + "/";
const reservationApiUri = urlSchema + hostName + projectName + reservationApi;
const imageDownloadUri = urlSchema + hostName + projectName + imageDownload;