const cassandra = require('cassandra-driver');

// Creating a Cassandra client
const client = new cassandra.Client({
  contactPoints: ['127.0.0.1'],  // Use your Astra DB URL if using Astra in the cloud
  localDataCenter: 'datacenter1', // Use the appropriate data center name
  keyspace: 'mykeyspace'          // The name of your keyspace in Cassandra
});

// Connect to the Cassandra database
client.connect()
  .then(() => console.log("Connected to Cassandra"))
  .catch(err => console.error("Error connecting to Cassandra", err));

module.exports = client;
