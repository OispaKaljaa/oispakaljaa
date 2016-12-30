const entrypoint = 'http://localhost:8080/api';
const APICall = {
  recommend: entrypoint + '/recommend',
  bars: entrypoint + '/bars',
  getBar: id => `${entrypoint}/bars/${id}`,
  getBarDrinks: id => `${entrypoint}/bars/${id}/drinks`
};