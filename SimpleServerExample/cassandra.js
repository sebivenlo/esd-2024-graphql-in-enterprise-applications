const cassandra = require('cassandra-driver');

// Creating a Cassandra client
const client = new cassandra.Client({
  contactPoints: ['127.0.0.1'],  
  localDataCenter: 'datacenter1', 
  keyspace: 'mykeyspace'          
});

// Connect to the Cassandra database
client.connect()
  .then(() => console.log("Connected to Cassandra"))
  .catch(err => console.error("Error connecting to Cassandra", err));

module.exports = client;
