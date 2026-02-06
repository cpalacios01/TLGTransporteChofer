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
import com.tlg.transporte.chofer.data.local.entities.GastoEntity;
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
public final class GastoDao_Impl implements GastoDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<GastoEntity> __insertionAdapterOfGastoEntity;

  private final SharedSQLiteStatement __preparedStmtOfMarkAsSynchronized;

  public GastoDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfGastoEntity = new EntityInsertionAdapter<GastoEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `gastos` (`localId`,`serverId`,`redondoLocalId`,`redondoServerId`,`concepto`,`monto`,`comprobante`,`descripcion`,`fechaHora`,`sincronizado`,`createdAt`,`updatedAt`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final GastoEntity entity) {
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
        statement.bindString(5, entity.getConcepto());
        statement.bindDouble(6, entity.getMonto());
        if (entity.getComprobante() == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.getComprobante());
        }
        if (entity.getDescripcion() == null) {
          statement.bindNull(8);
        } else {
          statement.bindString(8, entity.getDescripcion());
        }
        statement.bindString(9, entity.getFechaHora());
        final int _tmp = entity.getSincronizado() ? 1 : 0;
        statement.bindLong(10, _tmp);
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
        final String _query = "UPDATE gastos SET sincronizado = 1, serverId = ? WHERE localId = ?";
        return _query;
      }
    };
  }

  @Override
  public Object insert(final GastoEntity gasto, final Continuation<? super Long> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      @NonNull
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          final Long _result = __insertionAdapterOfGastoEntity.insertAndReturnId(gasto);
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
  public Flow<List<GastoEntity>> getGastosByRedondo(final long redondoLocalId) {
    final String _sql = "SELECT * FROM gastos WHERE redondoLocalId = ? ORDER BY fechaHora DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, redondoLocalId);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"gastos"}, new Callable<List<GastoEntity>>() {
      @Override
      @NonNull
      public List<GastoEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfLocalId = CursorUtil.getColumnIndexOrThrow(_cursor, "localId");
          final int _cursorIndexOfServerId = CursorUtil.getColumnIndexOrThrow(_cursor, "serverId");
          final int _cursorIndexOfRedondoLocalId = CursorUtil.getColumnIndexOrThrow(_cursor, "redondoLocalId");
          final int _cursorIndexOfRedondoServerId = CursorUtil.getColumnIndexOrThrow(_cursor, "redondoServerId");
          final int _cursorIndexOfConcepto = CursorUtil.getColumnIndexOrThrow(_cursor, "concepto");
          final int _cursorIndexOfMonto = CursorUtil.getColumnIndexOrThrow(_cursor, "monto");
          final int _cursorIndexOfComprobante = CursorUtil.getColumnIndexOrThrow(_cursor, "comprobante");
          final int _cursorIndexOfDescripcion = CursorUtil.getColumnIndexOrThrow(_cursor, "descripcion");
          final int _cursorIndexOfFechaHora = CursorUtil.getColumnIndexOrThrow(_cursor, "fechaHora");
          final int _cursorIndexOfSincronizado = CursorUtil.getColumnIndexOrThrow(_cursor, "sincronizado");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final List<GastoEntity> _result = new ArrayList<GastoEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final GastoEntity _item;
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
            final String _tmpConcepto;
            _tmpConcepto = _cursor.getString(_cursorIndexOfConcepto);
            final double _tmpMonto;
            _tmpMonto = _cursor.getDouble(_cursorIndexOfMonto);
            final String _tmpComprobante;
            if (_cursor.isNull(_cursorIndexOfComprobante)) {
              _tmpComprobante = null;
            } else {
              _tmpComprobante = _cursor.getString(_cursorIndexOfComprobante);
            }
            final String _tmpDescripcion;
            if (_cursor.isNull(_cursorIndexOfDescripcion)) {
              _tmpDescripcion = null;
            } else {
              _tmpDescripcion = _cursor.getString(_cursorIndexOfDescripcion);
            }
            final String _tmpFechaHora;
            _tmpFechaHora = _cursor.getString(_cursorIndexOfFechaHora);
            final boolean _tmpSincronizado;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfSincronizado);
            _tmpSincronizado = _tmp != 0;
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
            _item = new GastoEntity(_tmpLocalId,_tmpServerId,_tmpRedondoLocalId,_tmpRedondoServerId,_tmpConcepto,_tmpMonto,_tmpComprobante,_tmpDescripcion,_tmpFechaHora,_tmpSincronizado,_tmpCreatedAt,_tmpUpdatedAt);
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
  public Object getTotalGastos(final long redondoLocalId,
      final Continuation<? super Double> $completion) {
    final String _sql = "SELECT SUM(monto) FROM gastos WHERE redondoLocalId = ?";
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
  public Object getGastosNoSincronizados(
      final Continuation<? super List<GastoEntity>> $completion) {
    final String _sql = "SELECT * FROM gastos WHERE sincronizado = 0";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<GastoEntity>>() {
      @Override
      @NonNull
      public List<GastoEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfLocalId = CursorUtil.getColumnIndexOrThrow(_cursor, "localId");
          final int _cursorIndexOfServerId = CursorUtil.getColumnIndexOrThrow(_cursor, "serverId");
          final int _cursorIndexOfRedondoLocalId = CursorUtil.getColumnIndexOrThrow(_cursor, "redondoLocalId");
          final int _cursorIndexOfRedondoServerId = CursorUtil.getColumnIndexOrThrow(_cursor, "redondoServerId");
          final int _cursorIndexOfConcepto = CursorUtil.getColumnIndexOrThrow(_cursor, "concepto");
          final int _cursorIndexOfMonto = CursorUtil.getColumnIndexOrThrow(_cursor, "monto");
          final int _cursorIndexOfComprobante = CursorUtil.getColumnIndexOrThrow(_cursor, "comprobante");
          final int _cursorIndexOfDescripcion = CursorUtil.getColumnIndexOrThrow(_cursor, "descripcion");
          final int _cursorIndexOfFechaHora = CursorUtil.getColumnIndexOrThrow(_cursor, "fechaHora");
          final int _cursorIndexOfSincronizado = CursorUtil.getColumnIndexOrThrow(_cursor, "sincronizado");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final List<GastoEntity> _result = new ArrayList<GastoEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final GastoEntity _item;
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
            final String _tmpConcepto;
            _tmpConcepto = _cursor.getString(_cursorIndexOfConcepto);
            final double _tmpMonto;
            _tmpMonto = _cursor.getDouble(_cursorIndexOfMonto);
            final String _tmpComprobante;
            if (_cursor.isNull(_cursorIndexOfComprobante)) {
              _tmpComprobante = null;
            } else {
              _tmpComprobante = _cursor.getString(_cursorIndexOfComprobante);
            }
            final String _tmpDescripcion;
            if (_cursor.isNull(_cursorIndexOfDescripcion)) {
              _tmpDescripcion = null;
            } else {
              _tmpDescripcion = _cursor.getString(_cursorIndexOfDescripcion);
            }
            final String _tmpFechaHora;
            _tmpFechaHora = _cursor.getString(_cursorIndexOfFechaHora);
            final boolean _tmpSincronizado;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfSincronizado);
            _tmpSincronizado = _tmp != 0;
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
            _item = new GastoEntity(_tmpLocalId,_tmpServerId,_tmpRedondoLocalId,_tmpRedondoServerId,_tmpConcepto,_tmpMonto,_tmpComprobante,_tmpDescripcion,_tmpFechaHora,_tmpSincronizado,_tmpCreatedAt,_tmpUpdatedAt);
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
