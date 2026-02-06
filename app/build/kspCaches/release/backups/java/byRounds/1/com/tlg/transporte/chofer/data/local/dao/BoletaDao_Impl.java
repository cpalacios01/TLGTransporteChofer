package com.tlg.transporte.chofer.data.local.dao;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.tlg.transporte.chofer.data.local.entities.BoletaEntity;
import java.lang.Class;
import java.lang.Double;
import java.lang.Exception;
import java.lang.Integer;
import java.lang.Long;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class BoletaDao_Impl implements BoletaDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<BoletaEntity> __insertionAdapterOfBoletaEntity;

  private final SharedSQLiteStatement __preparedStmtOfMarkAsSynchronized;

  public BoletaDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfBoletaEntity = new EntityInsertionAdapter<BoletaEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `boletas` (`localId`,`serverId`,`redondoLocalId`,`redondoServerId`,`tarifaId`,`numeroBoleta`,`monto`,`fechaHora`,`createdOffline`,`sincronizado`,`createdAt`,`updatedAt`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final BoletaEntity entity) {
        statement.bindLong(1, entity.getLocalId());
        if (entity.getServerId() == null) {
          statement.bindNull(2);
        } else {
          statement.bindLong(2, entity.getServerId());
        }
        statement.bindLong(3, entity.getRedondoLocalId());
        if (entity.getRedondoServerId() == null) {
          statement.bindNull(4);
        } else {
          statement.bindLong(4, entity.getRedondoServerId());
        }
        statement.bindLong(5, entity.getTarifaId());
        statement.bindString(6, entity.getNumeroBoleta());
        statement.bindDouble(7, entity.getMonto());
        statement.bindString(8, entity.getFechaHora());
        final int _tmp = entity.getCreatedOffline() ? 1 : 0;
        statement.bindLong(9, _tmp);
        final int _tmp_1 = entity.getSincronizado() ? 1 : 0;
        statement.bindLong(10, _tmp_1);
        if (entity.getCreatedAt() == null) {
          statement.bindNull(11);
        } else {
          statement.bindString(11, entity.getCreatedAt());
        }
        if (entity.getUpdatedAt() == null) {
          statement.bindNull(12);
        } else {
          statement.bindString(12, entity.getUpdatedAt());
        }
      }
    };
    this.__preparedStmtOfMarkAsSynchronized = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "UPDATE boletas SET sincronizado = 1, serverId = ? WHERE localId = ?";
        return _query;
      }
    };
  }

  @Override
  public Object insert(final BoletaEntity boleta, final Continuation<? super Long> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      @NonNull
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          final Long _result = __insertionAdapterOfBoletaEntity.insertAndReturnId(boleta);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object markAsSynchronized(final long localId, final int serverId,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfMarkAsSynchronized.acquire();
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, serverId);
        _argIndex = 2;
        _stmt.bindLong(_argIndex, localId);
        try {
          __db.beginTransaction();
          try {
            _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return Unit.INSTANCE;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfMarkAsSynchronized.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<BoletaEntity>> getBoletasByRedondo(final long redondoLocalId) {
    final String _sql = "SELECT * FROM boletas WHERE redondoLocalId = ? ORDER BY fechaHora DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, redondoLocalId);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"boletas"}, new Callable<List<BoletaEntity>>() {
      @Override
      @NonNull
      public List<BoletaEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfLocalId = CursorUtil.getColumnIndexOrThrow(_cursor, "localId");
          final int _cursorIndexOfServerId = CursorUtil.getColumnIndexOrThrow(_cursor, "serverId");
          final int _cursorIndexOfRedondoLocalId = CursorUtil.getColumnIndexOrThrow(_cursor, "redondoLocalId");
          final int _cursorIndexOfRedondoServerId = CursorUtil.getColumnIndexOrThrow(_cursor, "redondoServerId");
          final int _cursorIndexOfTarifaId = CursorUtil.getColumnIndexOrThrow(_cursor, "tarifaId");
          final int _cursorIndexOfNumeroBoleta = CursorUtil.getColumnIndexOrThrow(_cursor, "numeroBoleta");
          final int _cursorIndexOfMonto = CursorUtil.getColumnIndexOrThrow(_cursor, "monto");
          final int _cursorIndexOfFechaHora = CursorUtil.getColumnIndexOrThrow(_cursor, "fechaHora");
          final int _cursorIndexOfCreatedOffline = CursorUtil.getColumnIndexOrThrow(_cursor, "createdOffline");
          final int _cursorIndexOfSincronizado = CursorUtil.getColumnIndexOrThrow(_cursor, "sincronizado");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final List<BoletaEntity> _result = new ArrayList<BoletaEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final BoletaEntity _item;
            final long _tmpLocalId;
            _tmpLocalId = _cursor.getLong(_cursorIndexOfLocalId);
            final Integer _tmpServerId;
            if (_cursor.isNull(_cursorIndexOfServerId)) {
              _tmpServerId = null;
            } else {
              _tmpServerId = _cursor.getInt(_cursorIndexOfServerId);
            }
            final long _tmpRedondoLocalId;
            _tmpRedondoLocalId = _cursor.getLong(_cursorIndexOfRedondoLocalId);
            final Integer _tmpRedondoServerId;
            if (_cursor.isNull(_cursorIndexOfRedondoServerId)) {
              _tmpRedondoServerId = null;
            } else {
              _tmpRedondoServerId = _cursor.getInt(_cursorIndexOfRedondoServerId);
            }
            final int _tmpTarifaId;
            _tmpTarifaId = _cursor.getInt(_cursorIndexOfTarifaId);
            final String _tmpNumeroBoleta;
            _tmpNumeroBoleta = _cursor.getString(_cursorIndexOfNumeroBoleta);
            final double _tmpMonto;
            _tmpMonto = _cursor.getDouble(_cursorIndexOfMonto);
            final String _tmpFechaHora;
            _tmpFechaHora = _cursor.getString(_cursorIndexOfFechaHora);
            final boolean _tmpCreatedOffline;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfCreatedOffline);
            _tmpCreatedOffline = _tmp != 0;
            final boolean _tmpSincronizado;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfSincronizado);
            _tmpSincronizado = _tmp_1 != 0;
            final String _tmpCreatedAt;
            if (_cursor.isNull(_cursorIndexOfCreatedAt)) {
              _tmpCreatedAt = null;
            } else {
              _tmpCreatedAt = _cursor.getString(_cursorIndexOfCreatedAt);
            }
            final String _tmpUpdatedAt;
            if (_cursor.isNull(_cursorIndexOfUpdatedAt)) {
              _tmpUpdatedAt = null;
            } else {
              _tmpUpdatedAt = _cursor.getString(_cursorIndexOfUpdatedAt);
            }
            _item = new BoletaEntity(_tmpLocalId,_tmpServerId,_tmpRedondoLocalId,_tmpRedondoServerId,_tmpTarifaId,_tmpNumeroBoleta,_tmpMonto,_tmpFechaHora,_tmpCreatedOffline,_tmpSincronizado,_tmpCreatedAt,_tmpUpdatedAt);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Object countBoletasByRedondo(final long redondoLocalId,
      final Continuation<? super Integer> $completion) {
    final String _sql = "SELECT COUNT(*) FROM boletas WHERE redondoLocalId = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, redondoLocalId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<Integer>() {
      @Override
      @NonNull
      public Integer call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Integer _result;
          if (_cursor.moveToFirst()) {
            final int _tmp;
            _tmp = _cursor.getInt(0);
            _result = _tmp;
          } else {
            _result = 0;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Object getTotalRecaudado(final long redondoLocalId,
      final Continuation<? super Double> $completion) {
    final String _sql = "SELECT SUM(monto) FROM boletas WHERE redondoLocalId = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, redondoLocalId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<Double>() {
      @Override
      @Nullable
      public Double call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Double _result;
          if (_cursor.moveToFirst()) {
            final Double _tmp;
            if (_cursor.isNull(0)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getDouble(0);
            }
            _result = _tmp;
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Object getBoletasNoSincronizadas(
      final Continuation<? super List<BoletaEntity>> $completion) {
    final String _sql = "SELECT * FROM boletas WHERE sincronizado = 0";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<BoletaEntity>>() {
      @Override
      @NonNull
      public List<BoletaEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfLocalId = CursorUtil.getColumnIndexOrThrow(_cursor, "localId");
          final int _cursorIndexOfServerId = CursorUtil.getColumnIndexOrThrow(_cursor, "serverId");
          final int _cursorIndexOfRedondoLocalId = CursorUtil.getColumnIndexOrThrow(_cursor, "redondoLocalId");
          final int _cursorIndexOfRedondoServerId = CursorUtil.getColumnIndexOrThrow(_cursor, "redondoServerId");
          final int _cursorIndexOfTarifaId = CursorUtil.getColumnIndexOrThrow(_cursor, "tarifaId");
          final int _cursorIndexOfNumeroBoleta = CursorUtil.getColumnIndexOrThrow(_cursor, "numeroBoleta");
          final int _cursorIndexOfMonto = CursorUtil.getColumnIndexOrThrow(_cursor, "monto");
          final int _cursorIndexOfFechaHora = CursorUtil.getColumnIndexOrThrow(_cursor, "fechaHora");
          final int _cursorIndexOfCreatedOffline = CursorUtil.getColumnIndexOrThrow(_cursor, "createdOffline");
          final int _cursorIndexOfSincronizado = CursorUtil.getColumnIndexOrThrow(_cursor, "sincronizado");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final List<BoletaEntity> _result = new ArrayList<BoletaEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final BoletaEntity _item;
            final long _tmpLocalId;
            _tmpLocalId = _cursor.getLong(_cursorIndexOfLocalId);
            final Integer _tmpServerId;
            if (_cursor.isNull(_cursorIndexOfServerId)) {
              _tmpServerId = null;
            } else {
              _tmpServerId = _cursor.getInt(_cursorIndexOfServerId);
            }
            final long _tmpRedondoLocalId;
            _tmpRedondoLocalId = _cursor.getLong(_cursorIndexOfRedondoLocalId);
            final Integer _tmpRedondoServerId;
            if (_cursor.isNull(_cursorIndexOfRedondoServerId)) {
              _tmpRedondoServerId = null;
            } else {
              _tmpRedondoServerId = _cursor.getInt(_cursorIndexOfRedondoServerId);
            }
            final int _tmpTarifaId;
            _tmpTarifaId = _cursor.getInt(_cursorIndexOfTarifaId);
            final String _tmpNumeroBoleta;
            _tmpNumeroBoleta = _cursor.getString(_cursorIndexOfNumeroBoleta);
            final double _tmpMonto;
            _tmpMonto = _cursor.getDouble(_cursorIndexOfMonto);
            final String _tmpFechaHora;
            _tmpFechaHora = _cursor.getString(_cursorIndexOfFechaHora);
            final boolean _tmpCreatedOffline;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfCreatedOffline);
            _tmpCreatedOffline = _tmp != 0;
            final boolean _tmpSincronizado;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfSincronizado);
            _tmpSincronizado = _tmp_1 != 0;
            final String _tmpCreatedAt;
            if (_cursor.isNull(_cursorIndexOfCreatedAt)) {
              _tmpCreatedAt = null;
            } else {
              _tmpCreatedAt = _cursor.getString(_cursorIndexOfCreatedAt);
            }
            final String _tmpUpdatedAt;
            if (_cursor.isNull(_cursorIndexOfUpdatedAt)) {
              _tmpUpdatedAt = null;
            } else {
              _tmpUpdatedAt = _cursor.getString(_cursorIndexOfUpdatedAt);
            }
            _item = new BoletaEntity(_tmpLocalId,_tmpServerId,_tmpRedondoLocalId,_tmpRedondoServerId,_tmpTarifaId,_tmpNumeroBoleta,_tmpMonto,_tmpFechaHora,_tmpCreatedOffline,_tmpSincronizado,_tmpCreatedAt,_tmpUpdatedAt);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
