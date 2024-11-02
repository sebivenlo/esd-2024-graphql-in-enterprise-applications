CREATE KEYSPACE IF NOT EXISTS graphql_keyspace 
WITH replication = {'class': 'SimpleStrategy', 'replication_factor': 1};
