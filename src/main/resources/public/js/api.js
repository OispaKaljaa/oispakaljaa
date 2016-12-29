const entrypoint = 'http://localhost:8080/api';
const APICall = {
  recommend: entrypoint + '/recommend',
  getBars: entrypoint + '/bars',
  getBar: id => `${entrypoint}/bars/${id}`,
  getBarDrinks: id => `${entrypoint}/bars/${id}/drinks`
};