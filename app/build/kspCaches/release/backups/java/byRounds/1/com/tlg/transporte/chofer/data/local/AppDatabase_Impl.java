package com.tlg.transporte.chofer.data.local;

import androidx.annotation.NonNull;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.RoomOpenHelper;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import com.tlg.transporte.chofer.data.local.dao.BoletaDao;
import com.tlg.transporte.chofer.data.local.dao.BoletaDao_Impl;
import com.tlg.transporte.chofer.data.local.dao.GastoDao;
import com.tlg.transporte.chofer.data.local.dao.GastoDao_Impl;
import com.tlg.transporte.chofer.data.local.dao.RedondoDao;
import com.tlg.transporte.chofer.data.local.dao.RedondoDao_Impl;
import com.tlg.transporte.chofer.data.local.dao.TarifaDao;
import com.tlg.transporte.chofer.data.local.dao.TarifaDao_Impl;
import com.tlg.transporte.chofer.data.local.dao.UserDao;
import com.tlg.transporte.chofer.data.local.dao.UserDao_Impl;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.Generated;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class AppDatabase_Impl extends AppDatabase {
  private volatile UserDao _userDao;

  private volatile TarifaDao _tarifaDao;

  private volatile RedondoDao _redondoDao;

  private volatile BoletaDao _boletaDao;

  private volatile GastoDao _gastoDao;

  @Override
  @NonNull
  protected SupportSQLiteOpenHelper createOpenHelper(@NonNull final DatabaseConfiguration config) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(config, new RoomOpenHelper.Delegate(1) {
      @Override
      public void createAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS `users` (`id` INTEGER NOT NULL, `nombre` TEXT NOT NULL, `email` TEXT NOT NULL, `roleId` INTEGER NOT NULL, `roleName` TEXT NOT NULL, `busId` INTEGER, `token` TEXT, PRIMARY KEY(`id`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS `tarifas` (`id` INTEGER NOT NULL, `nombre` TEXT NOT NULL, `monto` REAL NOT NULL, `distanciaKm` REAL, `descripcion` TEXT, `activo` INTEGER NOT NULL, `createdAt` TEXT, `updatedAt` TEXT, PRIMARY KEY(`id`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS `redondos` (`localId` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `serverId` INTEGER, `codigoUnico` TEXT, `fechaSalida` TEXT NOT NULL, `choferId` INTEGER NOT NULL, `busId` INTEGER NOT NULL, `itinerarioId` INTEGER NOT NULL, `montoInicial` REAL NOT NULL, `fechaRegreso` TEXT, `estado` TEXT NOT NULL, `sincronizado` INTEGER NOT NULL, `createdAt` TEXT, `updatedAt` TEXT)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `boletas` (`localId` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `serverId` INTEGER, `redondoLocalId` INTEGER NOT NULL, `redondoServerId` INTEGER, `tarifaId` INTEGER NOT NULL, `numeroBoleta` TEXT NOT NULL, `monto` REAL NOT NULL, `fechaHora` TEXT NOT NULL, `createdOffline` INTEGER NOT NULL, `sincronizado` INTEGER NOT NULL, `createdAt` TEXT, `updatedAt` TEXT)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `gastos` (`localId` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `serverId` INTEGER, `redondoLocalId` INTEGER NOT NULL, `redondoServerId` INTEGER, `concepto` TEXT NOT NULL, `monto` REAL NOT NULL, `comprobante` TEXT, `descripcion` TEXT, `fechaHora` TEXT NOT NULL, `sincronizado` INTEGER NOT NULL, `createdAt` TEXT, `updatedAt` TEXT)");
        db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '6dd3fdec841be245a5aa2cf5c8c4c33f')");
      }

      @Override
      public void dropAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS `users`");
        db.execSQL("DROP TABLE IF EXISTS `tarifas`");
        db.execSQL("DROP TABLE IF EXISTS `redondos`");
        db.execSQL("DROP TABLE IF EXISTS `boletas`");
        db.execSQL("DROP TABLE IF EXISTS `gastos`");
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onDestructiveMigration(db);
          }
        }
      }

      @Override
      public void onCreate(@NonNull final SupportSQLiteDatabase db) {
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onCreate(db);
          }
        }
      }

      @Override
      public void onOpen(@NonNull final SupportSQLiteDatabase db) {
        mDatabase = db;
        internalInitInvalidationTracker(db);
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onOpen(db);
          }
        }
      }

      @Override
      public void onPreMigrate(@NonNull final SupportSQLiteDatabase db) {
        DBUtil.dropFtsSyncTriggers(db);
      }

      @Override
      public void onPostMigrate(@NonNull final SupportSQLiteDatabase db) {
      }

      @Override
      @NonNull
      public RoomOpenHelper.ValidationResult onValidateSchema(
          @NonNull final SupportSQLiteDatabase db) {
        final HashMap<String, TableInfo.Column> _columnsUsers = new HashMap<String, TableInfo.Column>(7);
        _columnsUsers.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUsers.put("nombre", new TableInfo.Column("nombre", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUsers.put("email", new TableInfo.Column("email", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUsers.put("roleId", new TableInfo.Column("roleId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUsers.put("roleName", new TableInfo.Column("roleName", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUsers.put("busId", new TableInfo.Column("busId", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUsers.put("token", new TableInfo.Column("token", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysUsers = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesUsers = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoUsers = new TableInfo("users", _columnsUsers, _foreignKeysUsers, _indicesUsers);
        final TableInfo _existingUsers = TableInfo.read(db, "users");
        if (!_infoUsers.equals(_existingUsers)) {
          return new RoomOpenHelper.ValidationResult(false, "users(com.tlg.transporte.chofer.data.local.entities.UserEntity).\n"
                  + " Expected:\n" + _infoUsers + "\n"
                  + " Found:\n" + _existingUsers);
        }
        final HashMap<String, TableInfo.Column> _columnsTarifas = new HashMap<String, TableInfo.Column>(8);
        _columnsTarifas.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTarifas.put("nombre", new TableInfo.Column("nombre", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTarifas.put("monto", new TableInfo.Column("monto", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTarifas.put("distanciaKm", new TableInfo.Column("distanciaKm", "REAL", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTarifas.put("descripcion", new TableInfo.Column("descripcion", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTarifas.put("activo", new TableInfo.Column("activo", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTarifas.put("createdAt", new TableInfo.Column("createdAt", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTarifas.put("updatedAt", new TableInfo.Column("updatedAt", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysTarifas = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesTarifas = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoTarifas = new TableInfo("tarifas", _columnsTarifas, _foreignKeysTarifas, _indicesTarifas);
        final TableInfo _existingTarifas = TableInfo.read(db, "tarifas");
        if (!_infoTarifas.equals(_existingTarifas)) {
          return new RoomOpenHelper.ValidationResult(false, "tarifas(com.tlg.transporte.chofer.data.local.entities.TarifaEntity).\n"
                  + " Expected:\n" + _infoTarifas + "\n"
                  + " Found:\n" + _existingTarifas);
        }
        final HashMap<String, TableInfo.Column> _columnsRedondos = new HashMap<String, TableInfo.Column>(13);
        _columnsRedondos.put("localId", new TableInfo.Column("localId", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRedondos.put("serverId", new TableInfo.Column("serverId", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRedondos.put("codigoUnico", new TableInfo.Column("codigoUnico", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRedondos.put("fechaSalida", new TableInfo.Column("fechaSalida", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRedondos.put("choferId", new TableInfo.Column("choferId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRedondos.put("busId", new TableInfo.Column("busId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRedondos.put("itinerarioId", new TableInfo.Column("itinerarioId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRedondos.put("montoInicial", new TableInfo.Column("montoInicial", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRedondos.put("fechaRegreso", new TableInfo.Column("fechaRegreso", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRedondos.put("estado", new TableInfo.Column("estado", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRedondos.put("sincronizado", new TableInfo.Column("sincronizado", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRedondos.put("createdAt", new TableInfo.Column("createdAt", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRedondos.put("updatedAt", new TableInfo.Column("updatedAt", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysRedondos = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesRedondos = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoRedondos = new TableInfo("redondos", _columnsRedondos, _foreignKeysRedondos, _indicesRedondos);
        final TableInfo _existingRedondos = TableInfo.read(db, "redondos");
        if (!_infoRedondos.equals(_existingRedondos)) {
          return new RoomOpenHelper.ValidationResult(false, "redondos(com.tlg.transporte.chofer.data.local.entities.RedondoEntity).\n"
                  + " Expected:\n" + _infoRedondos + "\n"
                  + " Found:\n" + _existingRedondos);
        }
        final HashMap<String, TableInfo.Column> _columnsBoletas = new HashMap<String, TableInfo.Column>(12);
        _columnsBoletas.put("localId", new TableInfo.Column("localId", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsBoletas.put("serverId", new TableInfo.Column("serverId", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsBoletas.put("redondoLocalId", new TableInfo.Column("redondoLocalId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsBoletas.put("redondoServerId", new TableInfo.Column("redondoServerId", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsBoletas.put("tarifaId", new TableInfo.Column("tarifaId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsBoletas.put("numeroBoleta", new TableInfo.Column("numeroBoleta", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsBoletas.put("monto", new TableInfo.Column("monto", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsBoletas.put("fechaHora", new TableInfo.Column("fechaHora", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsBoletas.put("createdOffline", new TableInfo.Column("createdOffline", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsBoletas.put("sincronizado", new TableInfo.Column("sincronizado", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsBoletas.put("createdAt", new TableInfo.Column("createdAt", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsBoletas.put("updatedAt", new TableInfo.Column("updatedAt", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysBoletas = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesBoletas = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoBoletas = new TableInfo("boletas", _columnsBoletas, _foreignKeysBoletas, _indicesBoletas);
        final TableInfo _existingBoletas = TableInfo.read(db, "boletas");
        if (!_infoBoletas.equals(_existingBoletas)) {
          return new RoomOpenHelper.ValidationResult(false, "boletas(com.tlg.transporte.chofer.data.local.entities.BoletaEntity).\n"
                  + " Expected:\n" + _infoBoletas + "\n"
                  + " Found:\n" + _existingBoletas);
        }
        final HashMap<String, TableInfo.Column> _columnsGastos = new HashMap<String, TableInfo.Column>(12);
        _columnsGastos.put("localId", new TableInfo.Column("localId", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGastos.put("serverId", new TableInfo.Column("serverId", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGastos.put("redondoLocalId", new TableInfo.Column("redondoLocalId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGastos.put("redondoServerId", new TableInfo.Column("redondoServerId", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGastos.put("concepto", new TableInfo.Column("concepto", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGastos.put("monto", new TableInfo.Column("monto", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGastos.put("comprobante", new TableInfo.Column("comprobante", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGastos.put("descripcion", new TableInfo.Column("descripcion", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGastos.put("fechaHora", new TableInfo.Column("fechaHora", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGastos.put("sincronizado", new TableInfo.Column("sincronizado", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGastos.put("createdAt", new TableInfo.Column("createdAt", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGastos.put("updatedAt", new TableInfo.Column("updatedAt", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysGastos = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesGastos = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoGastos = new TableInfo("gastos", _columnsGastos, _foreignKeysGastos, _indicesGastos);
        final TableInfo _existingGastos = TableInfo.read(db, "gastos");
        if (!_infoGastos.equals(_existingGastos)) {
          return new RoomOpenHelper.ValidationResult(false, "gastos(com.tlg.transporte.chofer.data.local.entities.GastoEntity).\n"
                  + " Expected:\n" + _infoGastos + "\n"
                  + " Found:\n" + _existingGastos);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "6dd3fdec841be245a5aa2cf5c8c4c33f", "870e8ebad7d93ee52eb91db1dda3b4b0");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(config.context).name(config.name).callback(_openCallback).build();
    final SupportSQLiteOpenHelper _helper = config.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  @NonNull
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    final HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "users","tarifas","redondos","boletas","gastos");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `users`");
      _db.execSQL("DELETE FROM `tarifas`");
      _db.execSQL("DELETE FROM `redondos`");
      _db.execSQL("DELETE FROM `boletas`");
      _db.execSQL("DELETE FROM `gastos`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  @NonNull
  protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
    final HashMap<Class<?>, List<Class<?>>> _typeConvertersMap = new HashMap<Class<?>, List<Class<?>>>();
    _typeConvertersMap.put(UserDao.class, UserDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(TarifaDao.class, TarifaDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(RedondoDao.class, RedondoDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(BoletaDao.class, BoletaDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(GastoDao.class, GastoDao_Impl.getRequiredConverters());
    return _typeConvertersMap;
  }

  @Override
  @NonNull
  public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
    final HashSet<Class<? extends AutoMigrationSpec>> _autoMigrationSpecsSet = new HashSet<Class<? extends AutoMigrationSpec>>();
    return _autoMigrationSpecsSet;
  }

  @Override
  @NonNull
  public List<Migration> getAutoMigrations(
      @NonNull final Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> autoMigrationSpecs) {
    final List<Migration> _autoMigrations = new ArrayList<Migration>();
    return _autoMigrations;
  }

  @Override
  public UserDao userDao() {
    if (_userDao != null) {
      return _userDao;
    } else {
      synchronized(this) {
        if(_userDao == null) {
          _userDao = new UserDao_Impl(this);
        }
        return _userDao;
      }
    }
  }

  @Override
  public TarifaDao tarifaDao() {
    if (_tarifaDao != null) {
      return _tarifaDao;
    } else {
      synchronized(this) {
        if(_tarifaDao == null) {
          _tarifaDao = new TarifaDao_Impl(this);
        }
        return _tarifaDao;
      }
    }
  }

  @Override
  public RedondoDao redondoDao() {
    if (_redondoDao != null) {
      return _redondoDao;
    } else {
      synchronized(this) {
        if(_redondoDao == null) {
          _redondoDao = new RedondoDao_Impl(this);
        }
        return _redondoDao;
      }
    }
  }

  @Override
  public BoletaDao boletaDao() {
    if (_boletaDao != null) {
      return _boletaDao;
    } else {
      synchronized(this) {
        if(_boletaDao == null) {
          _boletaDao = new BoletaDao_Impl(this);
        }
        return _boletaDao;
      }
    }
  }

  @Override
  public GastoDao gastoDao() {
    if (_gastoDao != null) {
      return _gastoDao;
    } else {
      synchronized(this) {
        if(_gastoDao == null) {
          _gastoDao = new GastoDao_Impl(this);
        }
        return _gastoDao;
      }
    }
  }
}
