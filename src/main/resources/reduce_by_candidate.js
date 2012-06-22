function(key, values) {
    var result = {count: 0, amt: 0, candNm:""};
    values.forEach(function(value) {
      result.count += value.count;
      result.amt += value.amt;
      result.candNm = value.candNm;
    });
    return result;
}
