# demo_dataStore
datastore-preferences 的使用


將要存起來的資料用 PreferencesGroup 包裹，根據不同的 type 用不同的 Key(stringPreferencesKey,booleanPreferencesKey,longPreferencesKey... ) 來存。
```
class PreferencesGroup(private val mPref: DataStore<Preferences>) {
    val testString = DefDataStore(mPref, stringPreferencesKey("STRING"), "")
    val testBool = DefDataStore(mPref, booleanPreferencesKey("BOOL"), false)
    var testLong = DefDataStore(mPref, longPreferencesKey("NUMBER"), 0L)
    var defaultClass =
        ObjDataStore(mPref, stringPreferencesKey("CLASS"), ResponeData::class.java)
}
```

用 set or get 存和取值。

```
 mApp.preferencesGroup.testBool.set( binding.switchBool.isChecked)
```

```
  var bool = mApp.preferencesGroup.testBool.get()
```



注意要在 CoroutineScope 使用
```
 coroutineScope.launch {
                mApp.preferencesGroup.defaultClass.set(XXX)
            }
```

比較需要注意的地方是，因為 class 是轉成用 string 存，所以是使用 stringPreferencesKey
```
    var defaultClass =
        ObjDataStore(mPref, stringPreferencesKey("CLASS"), ResponeData::class.java)
```
