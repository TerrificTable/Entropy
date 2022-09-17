

HttpClient client = new HttpClient();
Console.WriteLine(new Http.Get("https://httpbin.org/get", "content/json").result);

