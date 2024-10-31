const cassandra = require('cassandra-driver');

const cassandraClient = new cassandra.Client({
  contactPoints: [process.env.CASSANDRA_CONTACT_POINTS || 'localhost'],
  localDataCenter: 'datacenter1',
  keyspace: process.env.CASSANDRA_KEYSPACE || 'workshop'
});

