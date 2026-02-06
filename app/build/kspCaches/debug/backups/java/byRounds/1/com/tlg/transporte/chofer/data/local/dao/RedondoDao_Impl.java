package com.tlg.transporte.chofer.data.local.dao;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.tlg.transporte.chofer.data.local.entities.RedondoEntity;
import java.lang.Class;
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
public final class RedondoDao_Impl implements RedondoDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<RedondoEntity> __insertionAdapterOfRedondoEntity;

  private final EntityDeletionOrUpdateAdapter<RedondoEntity> __updateAdapterOfRedondoEntity;

  private final SharedSQLiteStatement __preparedStmtOfMarkAsSynchronized;

  public RedondoDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfRedondoEntity = new EntityInsertionAdapter<RedondoEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `redondos` (`localId`,`serverId`,`codigoUnico`,`fechaSalida`,`choferId`,`busId`,`itinerarioId`,`montoInicial`,`fechaRegreso`,`estado`,`sincronizado`,`createdAt`,`updatedAt`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final RedondoEntity entity) {
        statement.bindLong(1, entity.getLocalId());
        if (entity.getServerId() == null) {
          statement.bindNull(2);
        } else {
          statement.bindLong(2, entity.getServerId());
        }
        if (entity.getCodigoUnico() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getCodigoUnico());
        }
        statement.bindString(4, entity.getFechaSalida());
        statement.bindLong(5, entity.getChoferId());
        statement.bindLong(6, entity.getBusId());
        statement.bindLong(7, entity.getItinerarioId());
        statement.bindDouble(8, entity.getMontoInicial());
        if (entity.getFechaRegreso() == null) {
          statement.bindNull(9);
        } else {
          statement.bindString(9, entity.getFechaRegreso());
        }
        statement.bindString(10, entity.getEstado());
        final int _tmp = entity.getSincronizado() ? 1 : 0;
        statement.bindLong(11, _tmp);
        if (entity.getCreatedAt() == null) {
          statement.bindNull(12);
        } else {
          statement.bindString(12, entity.getCreatedAt());
        }
        if (entity.getUpdatedAt() == null) {
          statement.bindNull(13);
        } else {
          statement.bindString(13, entity.getUpdatedAt());
        }
      }
    };
    this.__updateAdapterOfRedondoEntity = new EntityDeletionOrUpdateAdapter<RedondoEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `redondos` SET `localId` = ?,`serverId` = ?,`codigoUnico` = ?,`fechaSalida` = ?,`choferId` = ?,`busId` = ?,`itinerarioId` = ?,`montoInicial` = ?,`fechaRegreso` = ?,`estado` = ?,`sincronizado` = ?,`createdAt` = ?,`updatedAt` = ? WHERE `localId` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final RedondoEntity entity) {
        statement.bindLong(1, entity.getLocalId());
        if (entity.getServerId() == null) {
          statement.bindNull(2);
        } else {
          statement.bindLong(2, entity.getServerId());
        }
        if (entity.getCodigoUnico() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getCodigoUnico());
        }
        statement.bindString(4, entity.getFechaSalida());
        statement.bindLong(5, entity.getChoferId());
        statement.bindLong(6, entity.getBusId());
        statement.bindLong(7, entity.getItinerarioId());
        statement.bindDouble(8, entity.getMontoInicial());
        if (entity.getFechaRegreso() == null) {
          statement.bindNull(9);
        } else {
          statement.bindString(9, entity.getFechaRegreso());
        }
        statement.bindString(10, entity.getEstado());
        final int _tmp = entity.getSincronizado() ? 1 : 0;
        statement.bindLong(11, _tmp);
        if (entity.getCreatedAt() == null) {
          statement.bindNull(12);
        } else {
          statement.bindString(12, entity.getCreatedAt());
        }
        if (entity.getUpdatedAt() == null) {
          statement.bindNull(13);
        } else {
          statement.bindString(13, entity.getUpdatedAt());
        }
        statement.bindLong(14, entity.getLocalId());
      }
    };
    this.__preparedStmtOfMarkAsSynchronized = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "UPDATE redondos SET sincronizado = 1, serverId = ? WHERE localId = ?";
        return _query;
      }
    };
  }

  @Override
  public Object insert(final RedondoEntity redondo, final Continuation<? super Long> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      @NonNull
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          final Long _result = __insertionAdapterOfRedondoEntity.insertAndReturnId(redondo);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object update(final RedondoEntity redondo, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfRedondoEntity.handle(redondo);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
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
  public Flow<RedondoEntity> getRedondoActivo(final int choferId) {
    final String _sql = "SELECT * FROM redondos WHERE estado = 'abierto' AND choferId = ? LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, choferId);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"redondos"}, new Callable<RedondoEntity>() {
      @Override
      @Nullable
      public RedondoEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfLocalId = CursorUtil.getColumnIndexOrThrow(_cursor, "localId");
          final int _cursorIndexOfServerId = CursorUtil.getColumnIndexOrThrow(_cursor, "serverId");
          final int _cursorIndexOfCodigoUnico = CursorUtil.getColumnIndexOrThrow(_cursor, "codigoUnico");
          final int _cursorIndexOfFechaSalida = CursorUtil.getColumnIndexOrThrow(_cursor, "fechaSalida");
          final int _cursorIndexOfChoferId = CursorUtil.getColumnIndexOrThrow(_cursor, "choferId");
          final int _cursorIndexOfBusId = CursorUtil.getColumnIndexOrThrow(_cursor, "busId");
          final int _cursorIndexOfItinerarioId = CursorUtil.getColumnIndexOrThrow(_cursor, "itinerarioId");
          final int _cursorIndexOfMontoInicial = CursorUtil.getColumnIndexOrThrow(_cursor, "montoInicial");
          final int _cursorIndexOfFechaRegreso = CursorUtil.getColumnIndexOrThrow(_cursor, "fechaRegreso");
          final int _cursorIndexOfEstado = CursorUtil.getColumnIndexOrThrow(_cursor, "estado");
          final int _cursorIndexOfSincronizado = CursorUtil.getColumnIndexOrThrow(_cursor, "sincronizado");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final RedondoEntity _result;
          if (_cursor.moveToFirst()) {
            final long _tmpLocalId;
            _tmpLocalId = _cursor.getLong(_cursorIndexOfLocalId);
            final Integer _tmpServerId;
            if (_cursor.isNull(_cursorIndexOfServerId)) {
              _tmpServerId = null;
            } else {
              _tmpServerId = _cursor.getInt(_cursorIndexOfServerId);
            }
            final String _tmpCodigoUnico;
            if (_cursor.isNull(_cursorIndexOfCodigoUnico)) {
              _tmpCodigoUnico = null;
            } else {
              _tmpCodigoUnico = _cursor.getString(_cursorIndexOfCodigoUnico);
            }
            final String _tmpFechaSalida;
            _tmpFechaSalida = _cursor.getString(_cursorIndexOfFechaSalida);
            final int _tmpChoferId;
            _tmpChoferId = _cursor.getInt(_cursorIndexOfChoferId);
            final int _tmpBusId;
            _tmpBusId = _cursor.getInt(_cursorIndexOfBusId);
            final int _tmpItinerarioId;
            _tmpItinerarioId = _cursor.getInt(_cursorIndexOfItinerarioId);
            final double _tmpMontoInicial;
            _tmpMontoInicial = _cursor.getDouble(_cursorIndexOfMontoInicial);
            final String _tmpFechaRegreso;
            if (_cursor.isNull(_cursorIndexOfFechaRegreso)) {
              _tmpFechaRegreso = null;
            } else {
              _tmpFechaRegreso = _cursor.getString(_cursorIndexOfFechaRegreso);
            }
            final String _tmpEstado;
            _tmpEstado = _cursor.getString(_cursorIndexOfEstado);
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
            _result = new RedondoEntity(_tmpLocalId,_tmpServerId,_tmpCodigoUnico,_tmpFechaSalida,_tmpChoferId,_tmpBusId,_tmpItinerarioId,_tmpMontoInicial,_tmpFechaRegreso,_tmpEstado,_tmpSincronizado,_tmpCreatedAt,_tmpUpdatedAt);
          } else {
            _result = null;
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
  public Object getRedondosNoSincronizados(
      final Continuation<? super List<RedondoEntity>> $completion) {
    final String _sql = "SELECT * FROM redondos WHERE sincronizado = 0";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<RedondoEntity>>() {
      @Override
      @NonNull
      public List<RedondoEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfLocalId = CursorUtil.getColumnIndexOrThrow(_cursor, "localId");
          final int _cursorIndexOfServerId = CursorUtil.getColumnIndexOrThrow(_cursor, "serverId");
          final int _cursorIndexOfCodigoUnico = CursorUtil.getColumnIndexOrThrow(_cursor, "codigoUnico");
          final int _cursorIndexOfFechaSalida = CursorUtil.getColumnIndexOrThrow(_cursor, "fechaSalida");
          final int _cursorIndexOfChoferId = CursorUtil.getColumnIndexOrThrow(_cursor, "choferId");
          final int _cursorIndexOfBusId = CursorUtil.getColumnIndexOrThrow(_cursor, "busId");
          final int _cursorIndexOfItinerarioId = CursorUtil.getColumnIndexOrThrow(_cursor, "itinerarioId");
          final int _cursorIndexOfMontoInicial = CursorUtil.getColumnIndexOrThrow(_cursor, "montoInicial");
          final int _cursorIndexOfFechaRegreso = CursorUtil.getColumnIndexOrThrow(_cursor, "fechaRegreso");
          final int _cursorIndexOfEstado = CursorUtil.getColumnIndexOrThrow(_cursor, "estado");
          final int _cursorIndexOfSincronizado = CursorUtil.getColumnIndexOrThrow(_cursor, "sincronizado");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final List<RedondoEntity> _result = new ArrayList<RedondoEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final RedondoEntity _item;
            final long _tmpLocalId;
            _tmpLocalId = _cursor.getLong(_cursorIndexOfLocalId);
            final Integer _tmpServerId;
            if (_cursor.isNull(_cursorIndexOfServerId)) {
              _tmpServerId = null;
            } else {
              _tmpServerId = _cursor.getInt(_cursorIndexOfServerId);
            }
            final String _tmpCodigoUnico;
            if (_cursor.isNull(_cursorIndexOfCodigoUnico)) {
              _tmpCodigoUnico = null;
            } else {
              _tmpCodigoUnico = _cursor.getString(_cursorIndexOfCodigoUnico);
            }
            final String _tmpFechaSalida;
            _tmpFechaSalida = _cursor.getString(_cursorIndexOfFechaSalida);
            final int _tmpChoferId;
            _tmpChoferId = _cursor.getInt(_cursorIndexOfChoferId);
            final int _tmpBusId;
            _tmpBusId = _cursor.getInt(_cursorIndexOfBusId);
            final int _tmpItinerarioId;
            _tmpItinerarioId = _cursor.getInt(_cursorIndexOfItinerarioId);
            final double _tmpMontoInicial;
            _tmpMontoInicial = _cursor.getDouble(_cursorIndexOfMontoInicial);
            final String _tmpFechaRegreso;
            if (_cursor.isNull(_cursorIndexOfFechaRegreso)) {
              _tmpFechaRegreso = null;
            } else {
              _tmpFechaRegreso = _cursor.getString(_cursorIndexOfFechaRegreso);
            }
            final String _tmpEstado;
            _tmpEstado = _cursor.getString(_cursorIndexOfEstado);
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
            _item = new RedondoEntity(_tmpLocalId,_tmpServerId,_tmpCodigoUnico,_tmpFechaSalida,_tmpChoferId,_tmpBusId,_tmpItinerarioId,_tmpMontoInicial,_tmpFechaRegreso,_tmpEstado,_tmpSincronizado,_tmpCreatedAt,_tmpUpdatedAt);
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
