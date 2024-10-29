using NRedisStack;
using NRedisStack.RedisStackCommands;
using StackExchange.Redis;

public class RedisService : IDisposable
{
    private readonly ConnectionMultiplexer _redis;
    public IDatabase Database { get; }
    public IServer Server { get; }
    public JsonCommands JsonCommands { get; }

    public RedisService(string host, int port, string password)
    {
        var options = new ConfigurationOptions
        {
            EndPoints = { { host, port } },
            Password = password
        };

        this._redis = ConnectionMultiplexer.Connect(options);
        this.Database = _redis.GetDatabase();
        this.JsonCommands = Database.JSON();
        this.Server = this._redis.GetServer(_redis.GetEndPoints()[0]);
    }

    public void Dispose()
    {
        _redis?.Dispose();
    }
}
