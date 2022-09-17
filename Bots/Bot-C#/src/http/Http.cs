using System.Net.Http.Headers;



namespace Http {

    /**
     * Get(@String url)
     * Get(@String url ; @String content-type)
     * 
     * Get(...).result -> @String : result of http request
     */
    class Get {
        private static readonly HttpClient client = new HttpClient();
        private static String contentType = "content/text";
        public String result { get; set; } = "";



        public Get(String url, String contentTyp) {
            contentType = contentTyp;
            result = Task.Run(() => request(url)).GetAwaiter().GetResult();
        }

        public Get(String url) : this(url, contentType) {}



        private static async Task<String> request(String url) {
            client.DefaultRequestHeaders.Accept.Clear();
            client.DefaultRequestHeaders.Accept.Add(new MediaTypeWithQualityHeaderValue(contentType));
            client.DefaultRequestHeaders.Add("User-Agent", ".NET HttpClient");

            var stringTask = await client.GetStringAsync(url);
            return stringTask;
        }
    }

}
