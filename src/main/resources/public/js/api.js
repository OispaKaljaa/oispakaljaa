const entrypoint = location.origin + '/api';
const APICall = {
  me: entrypoint + '/me',
  recommend: entrypoint + '/recommend',
  bars: entrypoint + '/bars',
  barAction: id => `${entrypoint}/bars/${id}`,
  getBarDrinks: id => `${entrypoint}/bars/${id}/drinks`
};