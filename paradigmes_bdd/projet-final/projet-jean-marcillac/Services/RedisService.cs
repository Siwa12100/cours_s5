using StackExchange.Redis;

public class RedisService : IDisposable
{
    private readonly ConnectionMultiplexer _redis;
    public IDatabase Database { get; }

    public RedisService(string host, int port, string password)
    {
        var options = new ConfigurationOptions
        {
            EndPoints = { { host, port } },
            Password = password
        };

        _redis = ConnectionMultiplexer.Connect(options);
        Database = _redis.GetDatabase();
    }

    public void Dispose()
    {
        _redis?.Dispose();
    }
}
